package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AddressServiceImpl implements AddressService {


    private AddressRepository addressRepository;

    public AddressServiceImpl(@Qualifier("inMemoryAddressRepository") AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void add(Address address) {
        addressRepository.add(address);
    }

    @Override
    public void delete(int id) {
        addressRepository.delete(id);
    }

    @Override
    public Address findById(int id) {
         return addressRepository.findById(id);
    }

    @Override
    public Address findByName(String name) {
        return  addressRepository.findByName(name);
    }

    @Override
    public Address[] findAll() {
       return  addressRepository.findAll();
    }
}
