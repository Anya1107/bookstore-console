package by.bookstore.consoleapp.validator;

import by.bookstore.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator{

    @Autowired
    private String name2;

    public static boolean validAddressName(Address address) {
        return address.getAddress().length()>2;
    }
}
