package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.entity.Address;
import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.service.OrderService;
import by.bookstore.service.StoreService;

import java.util.Arrays;

public class OrderActionImpl implements OrderAction {
    private Reader reader;
    private Writer writer;
    private StoreService storeService;
    private OrderService orderService;

    public OrderActionImpl(Reader reader, Writer writer, StoreService storeService, OrderService orderService) {
        this.reader = reader;
        this.writer = writer;
        this.storeService = storeService;
        this.orderService = orderService;
    }

    @Override
    public void addDelivery() {
        writer.write("Введите адрес доставки:");
        String address=reader.read();
        Address address1=new Address(address);
        Order order=new Order(ConsoleApplicationImpl.activeSession.getUser(),ConsoleApplicationImpl.activeSession.getBasket().getBooks(), address1);
        orderService.add(order);
    }

    @Override
    public void addPickUp() {
       writer.write("Выберите пункт самовывоза:");
        Store[] stores = storeService.findAll();
        for (int i = 0; i <stores.length ; i++) {
            if(stores[i]==null) break;
            writer.write(i+" "+ stores[i].getName());
        }
        int x=reader.readInt();
        Store store = stores[x];
        Order order=new Order(store, ConsoleApplicationImpl.activeSession.getUser(),ConsoleApplicationImpl.activeSession.getBasket().getBooks());
        orderService.add(order);
    }

    @Override
    public void delete() {
        writer.write("Введите ID:");
        int id=reader.readInt();
        orderService.delete(id);
    }

    @Override
    public void findById() {
        writer.write("Введите ID:");
        int id=reader.readInt();
        Order order = orderService.findById(id);
        writer.write(order.getAddress().getAddress() + " " + order.getUser().getFirstName());
    }

    @Override
    public void findByStore() {
        writer.write("Выберите название магазина:");
        Store[] stores = storeService.findAll();
        for (int i = 0; i <stores.length ; i++) {
            writer.write(i+" "+stores[i].getName());
        }
        int x=reader.readInt();
        Store store = stores[x];
        Order order=orderService.findByStore(store);
        writer.write(order.getId()+" "+order.getUser().getFirstName());
    }

    @Override
    public void findAll() {
        Order[] orders = orderService.findAll();
        for (Order order : orders) {
            if(order==null) break;
            if(order.getAddress()==null){
                writer.write(order.getId() +" " + order.getUser().getFirstName()+" " + order.getStore().getName()+" " + Arrays.toString(order.getBooks()));
            }
            if(order.getStore()==null){
                writer.write(order.getId() +" " + order.getUser().getFirstName()+" " + order.getAddress()+" " + Arrays.toString(order.getBooks()) + " " +order.isDelivery());
            }
        }

    }

    @Override
    public void findAllByUser() {
        Order []orders=orderService.findAllByUser(ConsoleApplicationImpl.activeSession.getUser());
        for (int i = 0; i < orders.length; i++) {
            writer.write(i+ " " + orders[i].getStore().getName());
        }
    }

    @Override
    public void orderHistory() {
        Order []orders=orderService.findAllByUser(ConsoleApplicationImpl.activeSession.getUser());
        for(int i=0;i<orders.length;i++){
            writer.write(i+" "+orders[i].getDate()+ " "+Arrays.toString(orders[i].getBooks()));
        }
    }
}
