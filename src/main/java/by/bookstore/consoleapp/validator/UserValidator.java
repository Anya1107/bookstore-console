package by.bookstore.consoleapp.validator;

import by.bookstore.entity.User;

public interface UserValidator {
    boolean validFirstName(String firstName);
    boolean validLastName(String lastName);
    boolean validEmail(String email);
    boolean validPassword(String password);
}
