package by.bookstore.repository;

import by.bookstore.entity.Author;

public interface AuthorRepository {
    void add(Author author);
    void delete(int id);
    Author findByName(String name);
    Author findById(int id);
    Author[] findAll();
}
