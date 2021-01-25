package by.bookstore.service;

import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ModerServiceImpl implements ModerService {

    private OrderRepository orderRepository;

    public ModerServiceImpl(@Qualifier("inMemoryOrderRepository") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void updateInWait(Store store) {
        Order order = orderRepository.findByStore(store);

    }

    @Override
    public void updateInComplete(Store store) {

    }
}
