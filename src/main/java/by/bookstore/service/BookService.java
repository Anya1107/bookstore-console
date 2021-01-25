package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;

public interface BookService {
    void add(Book book);
    void delete(int id);
    Book[] findAll();
    Book findByTitle(String title);
    Book findById(int id);
    Book[] findByAuthorName(Author author);
}
