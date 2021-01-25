package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.entity.Moderator;
import by.bookstore.entity.Role;
import by.bookstore.entity.Store;
import by.bookstore.entity.User;
import by.bookstore.service.StoreService;
import by.bookstore.service.UserService;

public class AdminActionImpl implements AdminAction{

    private Writer writer;
    private Reader reader;
    private UserService userService;
    private StoreService storeService;

    public AdminActionImpl(Writer writer, Reader reader, UserService userService, StoreService storeService) {
        this.writer = writer;
        this.reader = reader;
        this.userService = userService;
        this.storeService = storeService;
    }

    @Override
    public void addModerator() {
        writer.write("Введите имя:");
        String firstName=reader.read();
        writer.write("Введите фамилию:");
        String lastName=reader.read();
        writer.write("Введите e-mail:");
        String email=reader.read();
        writer.write("Введите пароль:");
        String password=reader.read();
        writer.write("Выберите магазин:");
        Store[] stores = storeService.findAll();
        for (int i = 0; i <stores.length ; i++) {
            if(stores[i]==null) break;
            writer.write(i+" "+stores[i].getName());
        }
        int storeNum=reader.readInt();
        Store store = stores[storeNum];
        User user=new Moderator(firstName,lastName,email,password,store);
        user.setRole(Role.MODERATOR);
        userService.add(user);
    }

    @Override
    public void downModerator() {
        writer.write("Введите email модератора:");
        String email=reader.read();
        User moderator = userService.findByEmail(email);
        moderator.setRole(Role.USER);
    }

    @Override
    public void upModerator() {
        writer.write("Введите e-mail модератора:");
        String email=reader.read();
        User user=userService.findByEmail(email);
        user.setRole(Role.MODERATOR);
    }

    @Override
    public void deleteModerator() {
        writer.write("Введите e-mail модератора:");
        String email=reader.read();
        userService.deleteByEmail(email);
    }
}
