package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.consoleapp.validator.UserValidator;
import by.bookstore.entity.*;
import by.bookstore.service.OrderService;
import by.bookstore.service.StoreService;
import by.bookstore.service.UserService;

public class UserActionImpl implements UserAction {

    private UserService userService;
    private Writer writer;
    private Reader reader;
    private UserValidator userValidator;
    private OrderService orderService;
    private StoreService storeService;

    public UserActionImpl(UserService userService, Writer writer, Reader reader, UserValidator userValidator, OrderService orderService, StoreService storeService) {
        this.userService = userService;
        this.writer = writer;
        this.reader = reader;
        this.userValidator = userValidator;
        this.orderService = orderService;
        this.storeService = storeService;
    }

    @Override
    public void add() {
        writer.write("Введите имя: ");
        String firstName=reader.read();
        if(!userValidator.validFirstName(firstName)){
            writer.write("Ввели не те данные.");
            return;
        }
        writer.write("Введите фамилию:");
        String lastName=reader.read();
        if(!userValidator.validLastName(lastName)){
            writer.write("Ввели не те данные.");
            return;
        }
        writer.write("Введите e-mail:");
        String email=reader.read();
        if(!userValidator.validEmail(email)){
            writer.write("Ввели неверный e-mail.");
            return;
        }
        writer.write("Введите пароль: ");
        String password=reader.read();
        if(!userValidator.validPassword(password)){
            writer.write("Ввели неверный пароль.");
            return;
        }
        User user=new User(firstName,lastName,email,password, Role.USER);
        user.setRole(Role.USER);
        userService.add(user);
        if(user.getEmail().equals("admin@a.by")){
            user.setRole(Role.ADMIN);
        }
    }

    @Override
    public void delete() {
        writer.write("Введите ID для удаления:");
        int id=reader.readInt();
        userService.delete(id);
    }

    @Override
    public void findByEmail() {
        writer.write("Введите e-mail для поиска:");
        String email=reader.read();
        writer.write(userService.findByEmail(email).getFirstName());
    }

    @Override
    public void findById() {
        writer.write("Введите ID для поиска:");
        int id=reader.readInt();
        writer.write(userService.findById(id).getFirstName());
    }

    @Override
    public void findAll() {
        for (User user : userService.findAll()) {
            if (user == null) break;
            writer.write(user.getFirstName());
        }
    }

    @Override
    public void auth(){
        writer.write("Введите e-mail:");
        String email=reader.read();
        writer.write("Введите пароль:");
        String password=reader.read();
        User user = userService.findByEmail(email);
        if (user == null){
            writer.write("Пользователя с таким email не существует.");
            return;
        }
        if(user.getPassword().equals(password)){
            Session session = new Session();
            session.setUser(user);
            session.setBasket(new Basket(new Book[50]));
            ConsoleApplicationImpl.activeSession = session;
        }
    }

    @Override
    public void updateFistName() {
        writer.write("Введите новое имя:");
        String firstName=reader.read();
        userService.updateFirstName(firstName, ConsoleApplicationImpl.activeSession.getUser().getId());
    }

    @Override
    public void updateLastName() {
        writer.write("Введите новую фамилию:");
        String lastName=reader.read();
        userService.updateLastName(lastName,ConsoleApplicationImpl.activeSession.getUser().getId());
    }

    @Override
    public void createOrder() {
        User user = ConsoleApplicationImpl.activeSession.getUser();
        Book[] books = ConsoleApplicationImpl.activeSession.getBasket().getBooks();
        Store[] all = storeService.findAll();
        writer.write("Выберите пункт самовывоза: ");
        for (int i = 0; i <all.length; i++) {
            if(all[i]==null) break;
            writer.write(i+" "+all[i].getName());
        }
        int storeNum=reader.readInt();
        Store store = all[storeNum];
        Order order=new Order(store,user,books);
        ConsoleApplicationImpl.activeSession.getBasket().setBooks(new Book[10]);
        orderService.add(order);
    }

    @Override
    public void updatePassword() {
        writer.write("Введите новый пароль:");
        String password=reader.read();
        userService.updatePassword(password,ConsoleApplicationImpl.activeSession.getUser().getId());
    }
}