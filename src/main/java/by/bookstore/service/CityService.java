package by.bookstore.service;

import by.bookstore.entity.City;

public interface CityService {
    void add(City city);
    void delete(String name);
    City findByName(String name);
    City[] findAll();
}
