package by.bookstore.repository.file;

import by.bookstore.entity.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class FileDB implements DB {
    private static final String FILE_NAME = "data.txt";
    private static final File FILE = new File(FILE_NAME);
    private static Data data = new Data();

    {
        if (FILE.exists() && FILE.length() != 0) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE))) {
                data = (Data) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            data = new Data();
        }
    }


    private void writeData() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE));
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public <T> void write(List<T> list, Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "User":
                data.setUsers((List<User>) list);
                writeData();
                break;
            case "Address":
                data.setAddresses((List<Address>) list);
                writeData();
                break;
            case "Author":
                data.setAuthors((List<Author>) list);
                writeData();
                break;
            case "Basket":
                data.setBaskets((List<Basket>) list);
                writeData();
                break;
            case "City":
                data.setCities((List<City>) list);
                writeData();
                break;
            case "Order":
                data.setOrders((List<Order>) list);
                writeData();
                break;
            case "Store":
                data.setStores((List<Store>) list);
                writeData();
                break;
            case "Book":
                data.setBooks((List<Book>) list);
                writeData();
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
                writeData();
                break;
            case "Author":
                data.setAuthorId(id);
                writeData();
                break;
            case "Basket":
                data.setBasketId(id);
                writeData();
                break;
            case "Book":
                data.setBookId(id);
                writeData();
                break;
            case "City":
                data.setCityId(id);
                writeData();
                break;
            case "Order":
                data.setOrderId(id);
                writeData();
                break;
            case "Store":
                data.setStoreId(id);
                writeData();
                break;
            case "User":
                data.setUserId(id);
                writeData();
                break;
        }
    }
}
