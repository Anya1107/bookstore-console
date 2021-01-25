package by.bookstore.repository.db;

import by.bookstore.entity.*;
import by.bookstore.repository.OrderRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBOrderRepository extends DBAbstractRepository implements OrderRepository {

    private static final String INSERT_INTO_ORDERS = "insert into orders values (default, ?,?,?,?,?,?) returning id";
    private static final String INSERT_INTO_ORDER_BOOK = "insert into order_book values (?,?)";
    private static final String DELETE_FROM_ORDERS="delete from orders where id = ?";
    private static final String DELETE_FROM_ORDER_BOOK="delete from order_book where order_id = ?";
    private static final String SELECT_ALL_BOOKS_BY_ID="select * from books u join order_book ob on u.id = ob.book_id join authors a on a.id = u.author_id where ob.order_id = ?";
    private static final String SELECT_STORE="select * from stores s join orders o on s.id=o.store_id join cities c on s.city_id=c.id where s.id =?";
    private static final String SELECT_SEM="select * from orders o join stores s on o.store_id = s.id join users u on o.user_id = u.id join status st on o.status_id = st.id join addresses a on a.id = s.address_id join cities c on c.id = s.city_id join roles r on u.role_id=r.id where store_id =?";
    private static final String SELECT_ALL_ORDERS1="select * from orders o join stores s on o.store_id = s.id join users u on o.user_id = u.id join status st on o.status_id = st.id join addresses a on a.id = s.address_id join cities c on c.id = s.city_id join roles r on u.role_id=r.id";
    private static final String SELECT_ORDER_BY_ID1="select * from orders o join stores s on o.store_id = s.id join users u on o.user_id = u.id join status st on o.status_id = st.id join addresses a on a.id = s.address_id join cities c on c.id = s.city_id join roles r on u.role_id=r.id where o.id = ?";



    public static void main(String[] args) {
        DBOrderRepository dbOrderRepository = new DBOrderRepository();
//        System.out.println(dbOrderRepository.findById(8));
//        System.out.println(Arrays.toString(dbOrderRepository.findAll()));
        Store store=new Store(4,null,null,null);
        System.out.println(dbOrderRepository.findByStore(store));
//        System.out.println(Arrays.toString(dbOrderRepository.findAllByStore(store)));
//        User user=new User(null,null,null,null,Role.USER);
//        System.out.println(Arrays.toString(dbOrderRepository.findAllByUser(user)));
    }


    @Override
    public void add(Order order) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_ORDERS);
            if (order.isDelivery()){
                statement.setInt(1, 0);
                statement.setString(5, order.getAddress().getAddress());
            } else {
                statement.setInt(1, order.getStore().getId());
                statement.setString(5, "");
            }

            statement.setInt(2, order.getUser().getId());
            statement.setInt(3, 1);
            statement.setBoolean(4, order.isDelivery());

            statement.setDate(6, new Date(order.getDate().getTime()));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt(1);
            statement.close();

            for (Book book : order.getBooks()) {
                PreparedStatement statement1 = connection.prepareStatement(INSERT_INTO_ORDER_BOOK);
                statement1.setInt(1, orderId);
                statement1.setInt(2, book.getId());
                statement1.execute();
                statement1.close();
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement=connection.prepareStatement(DELETE_FROM_ORDERS);
            statement.setInt(1,id);
            statement.execute();
            statement.close();

            PreparedStatement statement1=connection.prepareStatement(DELETE_FROM_ORDER_BOOK);
            statement1.setInt(1,id);
            statement1.execute();
            statement1.close();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    @Override
    public Order findById(int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_ORDER_BY_ID1);
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt(1);
            String status = resultSet.getString(19);
            boolean isDelivery = resultSet.getBoolean(5);
            Date date = resultSet.getDate(7);

            User user=resultUser(resultSet);

            List<Book> books=new ArrayList<>();
            PreparedStatement statement3=connection.prepareStatement(SELECT_ALL_BOOKS_BY_ID);
            statement3.setInt(1,orderId);
            ResultSet resultSet3=statement3.executeQuery();
            books=resultBooks(resultSet3);
            Book []books1=books.toArray(new Book[0]);

            if(resultSet.getBoolean(5)){
                Order order=resultOrderWithAddress(resultSet,orderId,user,books1,status,isDelivery,date);
                return order;
            }else {
                Order order=resultOrderWithStore(resultSet, orderId,user,books1,status,isDelivery,date);
                return order;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order findByStore(Store store) {
        int storeId = store.getId();
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_SEM);
            statement.setInt(1,storeId);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(2);
                if(storeId==id){
                    statement.setInt(1,storeId);
                    int orderId = resultSet.getInt(1);
                    String status = resultSet.getString(19);
                    boolean isDelivery = resultSet.getBoolean(5);
                    Date date = resultSet.getDate(7);

                    User user=resultUser(resultSet);

                    List<Book> books=new ArrayList<>();
                    PreparedStatement statement3=connection.prepareStatement(SELECT_ALL_BOOKS_BY_ID);
                    statement3.setInt(1,orderId);
                    ResultSet resultSet3=statement3.executeQuery();
                    books=resultBooks(resultSet3);
                    Book[] books1 = books.toArray(new Book[0]);

                    Order order=resultOrderWithStore(resultSet,orderId,user,books1,status,isDelivery,date);

                    return order;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order[] findAll() {
        List<Order> orders=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_ALL_ORDERS1);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                String status = resultSet.getString(19);
                boolean isDelivery = resultSet.getBoolean(5);
                Date date = resultSet.getDate(7);

                User user=resultUser(resultSet);

                List<Book> books=new ArrayList<>();
                PreparedStatement statement3=connection.prepareStatement(SELECT_ALL_BOOKS_BY_ID);
                statement3.setInt(1,orderId);
                ResultSet resultSet3=statement3.executeQuery();
                books=resultBooks(resultSet3);
                Book[] books1 = books.toArray(new Book[0]);

                if(resultSet.getBoolean(5)){
                    Order order=resultOrderWithAddress(resultSet,orderId,user,books1,status,isDelivery,date);
                    orders.add(order);
                }else {
                    Order order=resultOrderWithStore(resultSet,orderId,user,books1,status,isDelivery,date);
                    orders.add(order);
                }
            }
            return orders.toArray(new Order[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order[] findAllByStore(Store store) {
        List<Order> orders=new ArrayList<>();
        int storeId = store.getId();
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_SEM);
            statement.setInt(1,storeId);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(2);
                if(storeId==id){
                    statement.setInt(1,storeId);
                    int orderId = resultSet.getInt(1);
                    String status = resultSet.getString(19);
                    boolean isDelivery = resultSet.getBoolean(5);
                    Date date = resultSet.getDate(7);

                    User user=resultUser(resultSet);

                    List<Book> books=new ArrayList<>();
                    PreparedStatement statement3=connection.prepareStatement(SELECT_ALL_BOOKS_BY_ID);
                    statement3.setInt(1,orderId);
                    ResultSet resultSet3=statement3.executeQuery();
                    books=resultBooks(resultSet3);
                    Book[] books1 = books.toArray(new Book[0]);

                    Order order=resultOrderWithStore(resultSet,orderId,user,books1,status,isDelivery,date);

                    orders.add(order);
                    return orders.toArray(new Order[0]);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order[] findAllByUser(User user) {
        List<Order> orders=new ArrayList<>();
        String userRole=user.getRole().toString();
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_ALL_ORDERS1);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                String role = resultSet.getString(25);
                if(userRole.equals(role)){
                    int orderId = resultSet.getInt(1);
                    String status = resultSet.getString(19);
                    boolean isDelivery = resultSet.getBoolean(5);
                    Date date = resultSet.getDate(7);

                    User user1=resultUser(resultSet);

                    List<Book> books=new ArrayList<>();
                    PreparedStatement statement3=connection.prepareStatement(SELECT_ALL_BOOKS_BY_ID);
                    statement3.setInt(1,orderId);
                    ResultSet resultSet3=statement3.executeQuery();
                    books=resultBooks(resultSet3);
                    Book[] books1 = books.toArray(new Book[0]);

                    if(resultSet.getBoolean(5)){
                        Order order=resultOrderWithAddress(resultSet,orderId,user1,books1,status,isDelivery,date);
                        orders.add(order);
                    }else {
                        Order order=resultOrderWithStore(resultSet,orderId,user1,books1,status,isDelivery,date);
                        orders.add(order);
                    }
                    return orders.toArray(new Order[0]);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private User resultUser(ResultSet resultSet1){
        try {
            int userId1 = resultSet1.getInt(3);
            String firstName = resultSet1.getString(13);
            String lastName = resultSet1.getString(14);
            String email = resultSet1.getString(15);
            String password = resultSet1.getString(16);
            String role = resultSet1.getString(25);

            User user=new User(userId1,firstName,lastName,email,password,Role.valueOf(role));
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private List<Book> resultBooks(ResultSet resultSet2){
        List<Book> books=new ArrayList<>();
        try{
            while (resultSet2.next()) {
                int bookId = resultSet2.getInt(7);
                String title = resultSet2.getString(2);
                int price = resultSet2.getInt(3);
                String description = resultSet2.getString(4);
                int authorId = resultSet2.getInt(8);
                String authorName = resultSet2.getString(9);

                Author author=new Author(authorName,authorId);
                Book book=new Book(bookId,title,author,price,description);

                books.add(book);
            }

            return books;

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    private Order resultOrderWithStore(ResultSet resultSet, int orderId,User user,Book[] books1,String status, boolean isDelivery, Date date){
        try{
            int storeId = resultSet.getInt(2);
            PreparedStatement statement3=connection.prepareStatement(SELECT_STORE);
            statement3.setInt(1,storeId);
            ResultSet resultSet3=statement3.executeQuery();
            resultSet3.next();
            int storeId1 = resultSet3.getInt(1);
            String storeName = resultSet3.getString(2);
            int addressId = resultSet3.getInt(3);
            String addressName = resultSet3.getString(10);
            int cityId = resultSet3.getInt(4);
            String cityName = resultSet3.getString(13);

            Address address=new Address(addressId,addressName);
            City city=new City(cityId,cityName);
            Store store=new Store(storeId1,storeName,address,city);
            Order order=new Order(orderId,store,user,books1,Status.valueOf(status),isDelivery,date);
            return order;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    private Order resultOrderWithAddress(ResultSet resultSet,int orderId,User user,Book[] books1, String status,boolean isDelivery,Date date){
        try{
            String addressName = resultSet.getString(6);
            Address address=new Address(addressName);
            Order order=new Order(orderId,user,books1,Status.valueOf(status),isDelivery,address,date);
            return order;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
