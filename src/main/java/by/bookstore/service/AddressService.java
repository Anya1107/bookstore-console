package by.bookstore.service;

import by.bookstore.entity.Address;

public interface AddressService {
    void add(Address address);
    void delete(int id);
    Address findById(int id);
    Address findByName(String name);
    Address[] findAll();
}
