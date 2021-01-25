package by.bookstore.service;


import by.bookstore.entity.Book;
import by.bookstore.entity.Store;

public interface StoreService {
    void add(Store store);
    void delete(int id);
    Store findById(int id);
    Store findByName(String name);
    Store[] findAll();
    void addBook(Book book, String storeName);
    Book findBookInStore(String title, String storeName);
    Book[] findAllBooksInStore(Store store);
    void deleteBookInStore(String title,String storeName);
}
