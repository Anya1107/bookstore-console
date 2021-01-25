package by.bookstore.repository.file;

import by.bookstore.entity.Book;
import by.bookstore.entity.Store;
import by.bookstore.repository.StoreRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileStoreRepository implements StoreRepository {
    private DB db = new FileDB();
    private List<Store> temp;


    {
       temp=db.read(Store.class);
    }

    public List<Store> getStoreTemp() {
        return temp;
    }

    @Override
    public void add(Store store) {
        int lastStoreId = db.getLastId(Store.class);
        store.setId(++lastStoreId);
        db.setId(lastStoreId,Store.class);
        temp.add(store);
        db.write(temp,Store.class);
    }

    @Override
    public void delete(int id) {
        for(Store store:temp){
            if(store==null) break;
            if(store.getId()==id){
                temp.remove(store);
            }
        }
        db.write(temp,Store.class);
    }

    @Override
    public Store findById(int id) {
        for(Store store:temp){
            if(store.getId()==id){
                return store;
            }
        }
        return null;
    }

    @Override
    public Store findByName(String name) {
        for(Store store:temp){
            if(store.getName().equals(name)){
                return store;
            }
        }
        return null;
    }

    @Override
    public Store[] findAll() {
        return temp.toArray(new Store[0]);
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

        return null;
    }

    @Override
    public void deleteBookInStore(String title, String storeName) {

    }
}
