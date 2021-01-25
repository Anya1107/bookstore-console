package by.bookstore.consoleapp.validator;

import by.bookstore.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreValidatorImpl implements StoreValidator {

    @Override
    public boolean validStoreName(Store store) {
        return store.getName().length()>2;
    }
}
