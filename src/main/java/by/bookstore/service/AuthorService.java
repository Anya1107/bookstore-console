package by.bookstore.service;

import by.bookstore.entity.Author;

public interface AuthorService {
    void add(Author author);
    void delete(int id);
    Author[] findAll();
    Author findByName(String name);
    Author findById(int id);
}
