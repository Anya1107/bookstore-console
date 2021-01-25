package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.entity.Moderator;
import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.service.OrderService;

import java.util.Arrays;

public class ModerActionImpl implements ModerAction {

    private Writer writer;
    private Reader reader;
    private OrderService orderService;

    public ModerActionImpl(Writer writer, Reader reader, OrderService orderService) {
        this.writer = writer;
        this.reader = reader;
        this.orderService = orderService;
    }

    @Override
    public void findAllOrders() {
        Moderator moderator = (Moderator) ConsoleApplicationImpl.activeSession.getUser();
        Store store = moderator.getStore();
        Order[] orders = orderService.findAllByStore(store);
        for (Order order : orders) {
            if (order == null) break;
            writer.write(String.valueOf(order.getId()));
            if(order.getAddress()!=null){
                writer.write(order.getAddress().getAddress());
            }
            writer.write(order.getStore().getName());
            writer.write(Arrays.toString(order.getBooks()));
            writer.write("================================");
        }

    }

    @Override
    public void updateStatusInOrder() {
//        writer.write("Выберите заказ:");
//        Order[] orders = orderService.findAllByStore(ConsoleApplicationImpl.activeSession.getUser());
//        for (Order order : orders) {
//            writer.write(order.getId() + " " + order.getAddress());
//        }
//        int x=reader.readInt();
//        Order order = orders[x];
//        writer.write("Выберите статус: 1 - Обрабатывается");
//        if (reader.readInt() == 1) {
//            order.setStatus(Order.PROCESSED_STATUS);
//        } else {
//            writer.write("Ввели не ту операцию!");
//        }
    }

    @Override
    public void closeOrder() {
//        writer.write("Выберите заказ:");
//        Order[] orders = orderService.findAllByStore(ConsoleApplicationImpl.activeSession.getUser().getOrder().getStore());
//        for (Order order : orders) {
//            writer.write(order.getId() + " " + order.getAddress());
//        }
//        int x=reader.readInt();
//        Order order = orders[x];
//        order.setStatus(Order.CLOSE_STATUS);
    }
}
