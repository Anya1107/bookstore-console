package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.entity.Book;
import by.bookstore.entity.Role;
import by.bookstore.entity.Session;


public class ConsoleApplicationImpl implements ConsoleApplication {
    public static Session activeSession;

    private Writer writer;
    private Reader reader;

    private AuthorAction authorAction;
    private BookAction bookAction;
    private CityAction cityAction;
    private StoreAction storeAction ;
    private AddressAction addressAction;
    private UserAction userAction;
    private AdminAction adminAction ;
    private OrderAction orderAction ;
    private ModerAction moderAction;

    public ConsoleApplicationImpl(Writer writer, Reader reader, AuthorAction authorAction, BookAction bookAction, CityAction cityAction, StoreAction storeAction, AddressAction addressAction, UserAction userAction, AdminAction adminAction, OrderAction orderAction, ModerAction moderAction) {
        this.writer = writer;
        this.reader = reader;
        this.authorAction = authorAction;
        this.bookAction = bookAction;
        this.cityAction = cityAction;
        this.storeAction = storeAction;
        this.addressAction = addressAction;
        this.userAction = userAction;
        this.adminAction = adminAction;
        this.orderAction = orderAction;
        this.moderAction = moderAction;
    }

    @Override
    public void run() {
        choose();
    }

    private void choose() {
        while (true) {
            if (activeSession == null) {
                writer.write("Привет, Гость.");
            } else {
                writer.write("Привет, " + activeSession.getUser().getFirstName() + " " + activeSession.getUser().getRole().name() + " Сессия ID " + activeSession.getId());
            }
            showMenu();
            if (activeSession == null) {
                switch (reader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        userAction.add();
                        continue;
                    case 2:
                        userAction.auth();
                        continue;
                    case 3:
                        bookAction.findByTitle();
                        continue;
                    case 4:
                        bookAction.findByAuthorName();
                        continue;
                    default:
                        writer.write("Такой операции не существует!");
                }
            }
            if (activeSession.getUser().getRole().equals(Role.USER)) {
                switch (reader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        authorAction.findByName();
                        continue;
                    case 2:
                        authorAction.findAll();
                        continue;
                    case 3:
                        bookAction.findByTitle();
                        continue;
                    case 4:
                        personalAccount();
                        switch (reader.readInt()) {
                            case 1:
                                userAction.updateFistName();
                                continue;
                            case 2:
                                userAction.updateLastName();
                                continue;
                            case 3:
                                userAction.updatePassword();
                                continue;
                            case 4:
                                orderHistoryMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        orderAction.orderHistory();
                                        continue;
                                    default:
                                        writer.write("Ввели не ту операцию!");
                                }
                                continue;
                            case 5:
                                basketMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        bookAction.deleteBookInBasket();
                                        continue;
                                    default:
                                        writer.write("Такой операции нет!");
                                }
                            case 6:
                                orderMenu();
                                switch (reader.readInt()) {
                                    case 1:
                                        orderAction.addDelivery();
                                        continue;
                                    case 2:
                                        orderAction.addPickUp();
                                        continue;
                                    case 0:
                                        continue;
                                    default:
                                        writer.write("Такой операции нет!");
                                }
                            case 7:
                                return;
                        }
                    case 5:
                        logout();
                        continue;
                    default:
                        writer.write("Такой операции не существует!");
                }
            }
            if (activeSession.getUser().getRole().equals(Role.MODERATOR)) {
                switch (reader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        bookAction.createBook();
                        continue;
                    case 2:
                        authorAction.findByName();
                        continue;
                    case 3:
                        authorAction.findAll();
                        continue;
                    case 4:
                        bookAction.findByTitle();
                        continue;
                    case 5:
                        personalAccount();
                        switch (reader.readInt()) {
                            case 1:
                                userAction.updateFistName();
                                continue;
                            case 2:
                                userAction.updateLastName();
                                continue;
                            case 3:
                                userAction.updatePassword();
                                continue;
                            case 4:
                                orderHistoryMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        orderAction.orderHistory();
                                        continue;
                                    default:
                                        writer.write("Ввели не ту операцию!");
                                }
                                continue;
                            case 5:
                                basketMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        return;
                                    case 1:
                                        bookAction.deleteBookInBasket();
                                        continue;
                                    case 2:
                                        userAction.createOrder();
                                        continue;
                                    default:
                                        writer.write("Такой операции нет!");
                                }
                            case 6:
                                orderMenu();
                                switch (reader.readInt()) {
                                    case 1:
                                        orderAction.addDelivery();
                                        continue;
                                    case 2:
                                        orderAction.addPickUp();
                                        continue;
                                    case 0:
                                        continue;
                                    default:
                                        writer.write("Ввели не ту операцию!");
                                }
                            case 7:
                                return;
                        }
                    case 6:
                        moderAction.findAllOrders();
                        continue;
                    case 7:
                        moderAction.updateStatusInOrder();
                        continue;
                    case 8:
                        moderAction.closeOrder();
                        continue;
                    case 9:
                        logout();
                        continue;
                    default:
                        writer.write("Такой операции не существует!");
                }
            }
            if (activeSession.getUser().getRole().equals(Role.ADMIN)) {
                switch (reader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        cityAction.add();
                        continue;
                    case 2:
                        cityAction.delete();
                        continue;
                    case 3:
                        addressAction.add();
                        continue;
                    case 4:
                        addressAction.delete();
                        continue;
                    case 5:
                        storeAction.createStore();
                        continue;
                    case 6:
                        storeAction.delete();
                        continue;
                    case 7:
                        authorAction.add();
                        continue;
                    case 8:
                        authorAction.delete();
                        continue;
                    case 9:
                        bookAction.createBook();
                        continue;
                    case 10:
                        bookAction.delete();
                        continue;
                    case 11:
                        storeAction.addBook();
                        continue;
                    case 12:
                        adminAction.addModerator();
                        continue;
                    case 13:
                        adminAction.downModerator();
                        continue;
                    case 14:
                        adminAction.upModerator();
                        continue;
                    case 15:
                        adminAction.deleteModerator();
                    case 16:
                        personalAccount();
                        switch (reader.readInt()) {
                            case 1:
                                userAction.updateFistName();
                                continue;
                            case 2:
                                userAction.updateLastName();
                                continue;
                            case 3:
                                userAction.updatePassword();
                                continue;
                            case 4:
                                orderHistoryMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        orderAction.orderHistory();
                                        continue;
                                    default:
                                        writer.write("Ввели не ту операцию!");
                                }
                                continue;
                            case 5:
                                basketMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        return;
                                    case 1:
                                        bookAction.deleteBookInBasket();
                                        continue;
                                    case 2:
                                        userAction.createOrder();
                                        continue;
                                    default:
                                        writer.write("Такой операции нет!");
                                }
                                continue;
                            case 7:
                                return;
                            default:
                                writer.write("Такой операции нет!");
                        }
                        continue;
                    case 17:
                        orderAction.findAll();
                    case 18:
                        orderAction.delete();
                        continue;
                    case 19:
                        orderAction.findById();
                        continue;
                    case 20:
                        orderAction.findByStore();
                        continue;
                    case 21:
                        logout();
                        continue;
                    default:
                        writer.write("Такой операции не существует!");
                }
            }
        }
    }


    private void getAllBookInBasket() {
        writer.write("Список книг в корзине:");
        int count = 0;
        for (Book book : activeSession.getBasket().getBooks()) {
            if (book == null) break;
            writer.write(count + " " + book.getTitle());
            count++;
        }
    }


    private void logout() {
        activeSession = null;
    }

    private void showMenu() {
        if (activeSession == null) {
            writer.write("0 - Выход.");
            writer.write("1 - Регистрация.");
            writer.write("2 - Авторизация.");
            writer.write("3 - Поиск книг по названию.");
            writer.write("4 - Поиск книг по автору.");
        } else if (activeSession.getUser().getRole().equals(Role.USER)) {
            writer.write("0 - Выход");
            writer.write("1 - Найти автора по имени.");
            writer.write("2 - Показать авторов.");
            writer.write("3 - Найти книгу по названию.");
            writer.write("4 - Войти в личный кабинет.");
            writer.write("5 - Выход из сессии.");
        } else if (activeSession.getUser().getRole().equals(Role.MODERATOR)) {
            writer.write("0 - Выход");
            writer.write("1 - Добавить книгу.");
            writer.write("2 - Найти автора по имени.");
            writer.write("3 - Показать авторов.");
            writer.write("4 - Найти книгу по названию.");
            writer.write("5 - Войти в личный кабинет.");
            writer.write("6 - Показать заказы.");
            writer.write("7 - Поменять статус заказа.");
            writer.write("8 - Закрыть заказ.");
            writer.write("9 - Выход из сессии.");
        } else if (activeSession.getUser().getRole().equals(Role.ADMIN)) {
            writer.write("0 - Выход");
            writer.write("1 - Добавить город.");
            writer.write("2 - Удалить город.");
            writer.write("3 - Добавить адрес.");
            writer.write("4 - Удалить адрес.");
            writer.write("5 - Добваить магазин.");
            writer.write("6 - Удалить магазин.");
            writer.write("7 - Добавить автора.");
            writer.write("8 - Удалить автора.");
            writer.write("9 - Добавить книгу.");
            writer.write("10 - Удалить книгу.");
            writer.write("11 - Добавить книгу со склада в магазин.");
            writer.write("12 - Создать аккаунт модератора.");
            writer.write("13 - Понизить модератора до пользователя.");
            writer.write("14 - Поднять пользователя до модератора.");
            writer.write("15 - Удалить модератора.");
            writer.write("16 - Войти в личный кабинет.");
            writer.write("17 - Вывести список заказов.");
            writer.write("18 - Удалить заказ.");
            writer.write("19 - Найти заказ по ID.");
            writer.write("20 - Найти заказ по магазину.");
            writer.write("21 - Выход из сессии.");
        }
    }

    private void personalAccount() {
        writer.write(activeSession.getUser().getRole().toString());
        writer.write("Имя и фамилия: " + activeSession.getUser().getFirstName() + " " + activeSession.getUser().getLastName());
        writer.write("E-mail: " + activeSession.getUser().getEmail());
//        if (activeSession.getUser().isOrder()) {
//            writer.write("Статус заказа " + activeSession.getUser().getOrder().getStatus());
//        }
        writer.write("Выберите операцию:");
        writer.write("1 - Изменить имя.");
        writer.write("2 - Изменить фамилию.");
        writer.write("3 - Изменить пароль.");
        writer.write("4 - История заказов.");
        if (activeSession.getUser().getRole().equals(Role.USER)) {
            writer.write("5 - Перейти в корзину.");
        }
        if (activeSession.getBasket().getBooks().length >= 1) {
            writer.write("6 - Оформить заказ.");
        }
        writer.write("7 - Выход.");
    }

    private void orderMenu() {
        Book[] books = activeSession.getBasket().getBooks();
        int count = 0;
        int price = 0;
        for (Book book : books) {
            if (book == null) break;
            count++;
            writer.write(count + " " + book.getTitle());
            price = price + book.getPrice();
        }
        writer.write("Сумма: " + price);
        writer.write("Выберите тип: ");
        writer.write("1 - Доставка.");
        writer.write("2 - Самовывоз");
        writer.write("0 - Выход.");
    }

    private void basketMenu() {
        Book[] books = activeSession.getBasket().getBooks();
        int count = 0;
        int price = 0;
        for (Book book : books) {
            if (book == null) break;
            count++;
            writer.write(count + " " + book.getTitle());
            price = price + book.getPrice();
        }
        writer.write("Сумма: " + price);
        writer.write("0 - Выход.");
        writer.write("1 - Удалить книгу. ");
    }

    private void orderHistoryMenu() {
        writer.write("0 - Выход.");
        writer.write("1 - Показать историю заказов.");
    }
}
