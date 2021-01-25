package by.bookstore.service;

import by.bookstore.entity.Book;
import by.bookstore.entity.Store;
import by.bookstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;

    public StoreServiceImpl(@Qualifier("inMemoryStoreRepository") StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void add(Store store) {
        storeRepository.add(store);
    }

    @Override
    public void delete(int id) {
        storeRepository.delete(id);
    }

    @Override
    public Store findById(int id) {
       return storeRepository.findById(id);
    }

    @Override
    public Store findByName(String name){
       return storeRepository.findByName(name);
    }

    @Override
    public void addBook(Book book, String storeName) {
       storeRepository.addBook(book,storeName);
    }

    @Override
    public Store[] findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Book findBookInStore(String title, String storeName) {
        return storeRepository.findBookInStore(title,storeName);
    }

    @Override
    public Book[] findAllBooksInStore(Store store) {
        return storeRepository.findAllBooksInStore(store);
    }

    @Override
    public void deleteBookInStore(String title,String storeName) {
        storeRepository.deleteBookInStore(title,storeName);
    }
}
