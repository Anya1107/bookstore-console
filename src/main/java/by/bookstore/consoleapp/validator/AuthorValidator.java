package by.bookstore.consoleapp.validator;

import by.bookstore.entity.Author;
import org.springframework.stereotype.Component;

@Component
public interface AuthorValidator {
    boolean valid(Author author);
}
