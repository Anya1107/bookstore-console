package by.bookstore.service;

import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.entity.User;
import by.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(@Qualifier("inMemoryOrderRepository") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(Order order) {
        orderRepository.add(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order findByStore(Store store) {
        return orderRepository.findByStore(store);
    }

    @Override
    public Order[] findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order[] findAllByStore(Store store) {
        return orderRepository.findAllByStore(store);
    }

    @Override
    public Order[] findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

}
