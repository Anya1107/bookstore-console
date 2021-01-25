package by.bookstore.repository;


import by.bookstore.entity.Author;
import by.bookstore.entity.Book;

public interface BookRepository {
    void add(Book book);
    void delete(int id);
    Book findByTitle(String title);
    Book findById(int id);
    Book[] findAll();
    Book[] findByAuthorName(Author author);
}
