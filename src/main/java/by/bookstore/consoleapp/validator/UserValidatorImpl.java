package by.bookstore.consoleapp.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidatorImpl implements UserValidator {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean validFirstName(String firstName) {
        return firstName.length()>2;
    }

    @Override
    public boolean validLastName(String lastName) {
        return lastName.length()>2;
    }

    @Override
    public boolean validEmail(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern= Pattern.compile(EMAIL_PATTERN);
        matcher=pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean validPassword(String password) {
        final String PASSWORD_PATTERN="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        pattern=Pattern.compile(PASSWORD_PATTERN);
        matcher=pattern.matcher(password);
        return matcher.matches();
    }
}
