package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.consoleapp.validator.StoreValidator;
import by.bookstore.entity.Address;
import by.bookstore.entity.Book;
import by.bookstore.entity.City;
import by.bookstore.entity.Store;
import by.bookstore.service.AddressService;
import by.bookstore.service.BookService;
import by.bookstore.service.CityService;
import by.bookstore.service.StoreService;

public class StoreActionImpl implements StoreAction {
    private Writer writer;
    private Reader reader;
    private CityService cityService;
    private BookService bookService;
    private AddressService addressService;
    private StoreValidator storeValidator;
    private StoreService storeService;

    public StoreActionImpl(Writer writer, Reader reader, CityService cityService, BookService bookService, AddressService addressService, StoreValidator storeValidator, StoreService storeService) {
        this.writer = writer;
        this.reader = reader;
        this.cityService = cityService;
        this.bookService = bookService;
        this.addressService = addressService;
        this.storeValidator = storeValidator;
        this.storeService = storeService;
    }

    @Override
    public void createStore() {
        City city = getCityList();
        writer.write("Выберите адрес магазина:");
        Address address = getAddress();
        writer.write("Введите название магазина:");
        String storeName = reader.read();
        if (!checkStoreName(storeName)) {
            writer.write("Магазин с таким именем уже существует!");
            return;
        }
        Store store=new Store(storeName,city,address);
        if(storeValidator.validStoreName(store)){
            storeService.add(store);
            return;
        }
        writer.write("Ввели не те данные.");
    }

    private boolean checkStoreName(String storeName){
        return storeService.findByName(storeName) == null;
    }

    private Address getAddress() {
        int count=0;
        for(Address address:addressService.findAll()){
            if(address==null) break;
            writer.write(count+" "+ address.getAddress());
            count++;
        }
        int i=reader.readInt();
        return addressService.findAll()[i];
    }

    private City getCityList() {
        writer.write("Выберите город из списка:");
        int count = 0;
        for (City city : cityService.findAll()) {
            if (city == null) break;
            writer.write(count + " " + city.getName());
            count++;
        }
        int i = reader.readInt();
        return cityService.findAll()[i];

    }

    @Override
    public void delete() {
        writer.write("Введите ID магазина для удаления:");
        int id = reader.readInt();
        storeService.delete(id);
    }

    @Override
    public void findById() {
        writer.write("Введите ID магазина для поиска:");
        int id = reader.readInt();
        writer.write(storeService.findById(id).getName());
    }

    @Override
    public void findByName() {
        writer.write("Введите название для поиска:");
        String name = reader.read();
        writer.write(String.valueOf(storeService.findByName(name).getId()));
    }

    @Override
    public void addBook() {
        writer.write("Введите имя книги на складе:");
        String bookName = reader.read();
        if(bookService.findByTitle(bookName)==null){
            writer.write("Такой книги на складе нет!");
            return;
        }
        Book book = bookService.findByTitle(bookName);
        bookService.delete(book.getId());
        writer.write("Введите имя магазина:");
        String storeName = reader.read();
        storeService.addBook(book, storeName);
    }


    @Override
    public void findAll() {
        int count=0;
        for(Store store:storeService.findAll()){
            if(store==null){
                writer.write("Список пуст!");
                break;
            }
            writer.write("Список магазинов:");
            writer.write(count+" "+store.getName());
            count++;
        }
    }
}
