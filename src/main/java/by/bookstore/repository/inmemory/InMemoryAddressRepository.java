package by.bookstore.repository.inmemory;

import by.bookstore.entity.Address;
import by.bookstore.repository.AddressRepository;
import by.bookstore.repository.file.DB;
import by.bookstore.repository.file.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryAddressRepository implements AddressRepository {
    private DB db=new InMemoryDB();
    private List<Address> addresses;

    {
        addresses=db.read(Address.class);
    }


    @Override
    public void add(Address address) {
        int lastAddressId = db.getLastId(Address.class);
        address.setId(++lastAddressId);
        db.setId(lastAddressId,Address.class);
        addresses.add(address);
        db.write(addresses,Address.class);
    }

    @Override
    public void delete(int id) {
        for (Address address:addresses){
            if(address.getId()==id){
                addresses.remove(address);
            }
        }
        db.write(addresses,Address.class);
    }

    @Override
    public Address findById(int id) {
        for (Address address : addresses) {
            if (address == null) break;
            if (address.getId() == id) {
                return address;
            }
        }
        return null;
    }

    @Override
    public Address findByName(String name) {
        for (Address address : addresses) {
            if (address == null) break;
            if (address.getAddress().equals(name)) {
                return address;
            }
        }
        return null;
    }

    @Override
    public Address[] findAll() {
        return addresses.toArray(new Address[0]);
    }
}
