package by.bookstore.repository;

import by.bookstore.entity.Address;

public interface AddressRepository {
    void add(Address address);
    void delete(int id);
    Address findById(int id);
    Address findByName(String name);
    Address[] findAll();
}
