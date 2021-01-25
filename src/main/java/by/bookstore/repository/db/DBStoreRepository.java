package by.bookstore.repository.db;

import by.bookstore.entity.Address;
import by.bookstore.entity.Book;
import by.bookstore.entity.City;
import by.bookstore.entity.Store;
import by.bookstore.repository.StoreRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBStoreRepository extends DBAbstractRepository implements StoreRepository {

    private static final String INSERT="insert into stores values (default,?,?,?)";
    private static final String DELETE_BY_ID="delete from stores where id = ?";
    private static final String SELECT_BY_ID="select * from stores s join addresses a on s.address_id=a.id join cities c on s.city_id=c.id where s.id = ?";
    private static final String SELECT_BY_NAME="select * from stores s join addresses a on s.address_id=a.id join cities c on s.city_id=c.id  where s.name = ?";
    private static final String SELECT_ALL="select * from stores s join addresses a on s.address_id=a.id join cities c on s.city_id=c.id";


    public static void main(String[] args) {
        DBStoreRepository dbStoreRepository=new DBStoreRepository();
       // dbStoreRepository.add(new Store("test",new Address(4,"test"),new City(4,"minsk")));
        //dbStoreRepository.add(new Store("test1",new Address(4,"test"),new City(4,"minsk")));
       // dbStoreRepository.delete(3);
        //System.out.println(dbStoreRepository.findById(4));
       //System.out.println(dbStoreRepository.findByName("test"));
        //System.out.println(Arrays.toString(dbStoreRepository.findAll()));
       // dbStoreRepository.deleteBookInStore("test4","store");
        System.out.println(dbStoreRepository.findBookInStore("test4","store"));
    }

    @Override
    public void add(Store store) {
        try {
            PreparedStatement statement=connection.prepareStatement(INSERT);
            statement.setString(1,store.getName());
            statement.setInt(2,store.getAddress().getId());
            statement.setInt(3,store.getCity().getId());
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Store findById(int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            Store store=resultStore(resultSet);
            statement.close();
            return store;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store findByName(String name) {
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_BY_NAME);
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            Store store=resultStore(resultSet);
            statement.close();
            return store;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store[] findAll() {
        try {
            List<Store> stores=new ArrayList<>();
            PreparedStatement statement=connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                Store store=resultStore(resultSet);
                stores.add(store);
            }
            resultSet.close();
            statement.close();
            return stores.toArray(new Store[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void addBook(Book book, String storeName) {

    }

    @Override
    public Book findBookInStore(String title, String storeName) {
        return null;
    }

    @Override
    public Book[] findAllBooksInStore(Store store) {
        return new Book[0];
    }

    @Override
    public void deleteBookInStore(String title, String storeName) {

    }

    private Store resultStore(ResultSet resultSet){
        try {
            int id = resultSet.getInt(1);
            String name= resultSet.getString(2);
            int addressId = resultSet.getInt(6);
            String address = resultSet.getString(7);
            int cityId = resultSet.getInt(8);
            String city = resultSet.getString(9);

            Address address1=new Address(addressId,address);
            City city1 = new City(cityId,city);
            Store store=new Store(id, name, address1, city1);

            return store;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
