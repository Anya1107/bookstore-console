package by.bookstore.repository.file;


import by.bookstore.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryDB implements DB{
    private static Data data = new Data();

    @Override
    public <T> void write(List<T> list, Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "User":
                data.setUsers((List<User>) list);
                break;
            case "Address":
                data.setAddresses((List<Address>) list);
                break;
            case "Author":
                data.setAuthors((List<Author>) list);
                break;
            case "Basket":
                data.setBaskets((List<Basket>) list);
                break;
            case "City":
                data.setCities((List<City>) list);
                break;
            case "Order":
                data.setOrders((List<Order>) list);
                break;
            case "Store":
                data.setStores((List<Store>) list);
                break;
            case "Book":
                data.setBooks((List<Book>) list);
                break;
        }
    }

    @Override
    public <T> List<T> read(Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "Address":
                return (List<T>) data.getAddresses();
            case "Author":
                return (List<T>) data.getAuthors();
            case "Basket":
                return (List<T>) data.getBaskets();
            case "Book":
                return (List<T>) data.getBooks();
            case "City":
                return (List<T>) data.getCities();
            case "Order":
                return (List<T>) data.getOrders();
            case "Store":
                return (List<T>) data.getStores();
            case "User":
                return (List<T>) data.getUsers();
        }
        return null;
    }

    @Override
    public int getLastId(Class<?> clazz) {
        switch (clazz.getSimpleName()) {
            case "Address":
                return data.getAddressId();
            case "Author":
                return data.getAuthorId();
            case "Basket":
                return data.getBasketId();
            case "Book":
                return data.getBookId();
            case "City":
                return data.getCityId();
            case "Order":
                return data.getOrderId();
            case "Store":
                return data.getStoreId();
            case "User":
                return data.getUserId();
        }
        return 0;
    }

    @Override
    public void setId(int id, Class<?> clazz) {
        switch (clazz.getSimpleName()) {
            case "Address":
                data.setAddressId(id);
                break;
            case "Author":
                data.setAuthorId(id);
                break;
            case "Basket":
                data.setBasketId(id);
                break;
            case "Book":
                data.setBookId(id);
                break;
            case "City":
                data.setCityId(id);
                break;
            case "Order":
                data.setOrderId(id);
                break;
            case "Store":
                data.setStoreId(id);
                break;
            case "User":
                data.setUserId(id);
                break;
        }
    }
}
