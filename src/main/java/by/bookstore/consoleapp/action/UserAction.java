package by.bookstore.consoleapp.action;

import by.bookstore.entity.User;

public interface UserAction {
    void add();
    void delete();
    void findByEmail();
    void findById();
    void findAll();
    void auth();
    void updateFistName();
    void updateLastName();
    void updatePassword();
    void createOrder();

}
