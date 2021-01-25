package by.bookstore.repository.inmemory;

import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.entity.User;
import by.bookstore.repository.OrderRepository;
import by.bookstore.repository.file.DB;
import by.bookstore.repository.file.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryOrderRepository implements OrderRepository {
    private DB db = new InMemoryDB();
    private List<Order> orders;

    {
        orders= db.read(Order.class);
    }

    @Override
    public void add(Order order) {
        int lastOrderId= db.getLastId(Order.class);
        order.setId(++lastOrderId);
        db.setId(lastOrderId,Order.class);
        orders.add(order);
        db.write(orders,Order.class);
    }

    @Override
    public void delete(int id) {
        for(Order order:orders){
            if(order.getId()==id){
                orders.remove(order);
            }
        }
        db.write(orders,Order.class);
    }

    @Override
    public Order findById(int id) {
        for (Order order : orders) {
            if (order == null) break;
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order findByStore(Store store) {
        for(Order order:orders){
            if(order==null) break;
            if(order.getStore().equals(store)){
                return order;
            }
        }
        return null;
    }

    @Override
    public Order[] findAll() {
        return orders.toArray(new Order[0]);
    }

    @Override
    public Order[] findAllByStore(Store store) {
        List<Order> orders1=new ArrayList<>();
        for(Order order:orders){
            if(order.getStore().equals(store)){
                orders1.add(order);
            }
        }
        return orders1.toArray(new Order[0]);
    }

    @Override
    public Order[] findAllByUser(User user) {
        List<Order> orders1=new ArrayList<>();
        for(Order order:orders){
            if(order.getUser().equals(user)){
                orders1.add(order);
            }
        }
        return orders1.toArray(new Order[0]);
    }

    public static int count = 0;

    public static void req(){
       if (count < 10) {
           System.out.println(count);
           count++;
           req();
       }
    }

    public static void main(String[] args) {
        req();
    }
}
