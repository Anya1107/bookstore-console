package by.bookstore.repository;

import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.entity.Store;

public interface BasketRepository {
    void addBook(Basket basket, Book book, Store store);
}
