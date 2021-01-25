package by.bookstore.service;

import by.bookstore.entity.User;

public interface UserService {
    void add(User user);
    void delete(int id);
    User findByEmail(String email);
    User findById(int id);
    User[] findAll();
    void updateFirstName(String firstName, int id);
    void updateLastName(String lastName, int id);
    void updatePassword(String password, int id);
    void deleteByEmail(String email);
}
