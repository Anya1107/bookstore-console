package by.bookstore.repository.file;

import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.entity.User;
import by.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileOrderRepository implements OrderRepository {
    private DB db = new FileDB();
    private List<Order> temp;


    {
        temp=db.read(Order.class);
    }

    public List<Order> getOrderTemp() {
        return temp;
    }

    @Override
    public void add(Order order) {
        int lastOrderId = db.getLastId(Order.class);
        order.setId(++lastOrderId);
        db.setId(lastOrderId,Order.class);
        temp.add(order);
        db.write(temp,Order.class);
    }

    @Override
    public void delete(int id) {
        for(Order order:temp){
            if(order==null) break;
            if(order.getId()==id){
                temp.remove(order);
            }
        }
        db.write(temp,Order.class);
    }

    @Override
    public Order findById(int id) {
        for(Order order:temp){
            if(order.getId()==id){
                return order;
            }
        }
        return null;
    }

    @Override
    public Order findByStore(Store store) {
        for(Order order:temp){
            if(order.getStore().equals(store)){
                return order;
            }
        }
        return null;
    }

    @Override
    public Order[] findAll() {
        return temp.toArray(new Order[0]);
    }

    @Override
    public Order[] findAllByStore(Store store) {
        Order[] orders1=new Order[50];
        for (int i = 0; i <orders1.length ; i++) {
            for(Order order:temp){
                if(order==null) break;
                if(order.getStore().equals(store)){
                    orders1[i]=order;
                }
            }
        }
        return orders1;
    }

    @Override
    public Order[] findAllByUser(User user) {
        Order[] orders1=new Order[50];
        for (int i = 0; i <orders1.length ; i++) {
            for(Order order:temp){
                if(order==null) break;
                if(order.getUser().equals(user)){
                    orders1[i]=order;
                }
            }
        }
        return orders1;
    }
}
