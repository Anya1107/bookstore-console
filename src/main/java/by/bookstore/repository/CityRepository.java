package by.bookstore.repository;

import by.bookstore.entity.City;

public interface CityRepository {
    void add(City city);
    void delete(String name);
    City findByName(String name);
    City[] findAll();
}
