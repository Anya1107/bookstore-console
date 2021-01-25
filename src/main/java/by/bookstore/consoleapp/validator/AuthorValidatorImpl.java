package by.bookstore.consoleapp.validator;

import by.bookstore.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidatorImpl implements AuthorValidator {

    @Override
    public boolean valid(Author author) {
        return author.getName().length()>2;
    }
}
