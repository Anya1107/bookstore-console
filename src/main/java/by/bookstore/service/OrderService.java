package by.bookstore.service;

import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.entity.User;

public interface OrderService {
    void add(Order order);
    void delete(int id);
    Order findById(int id);
    Order findByStore(Store store);
    Order[] findAll();
    Order[] findAllByStore(Store store);
    Order[] findAllByUser(User user);
}
