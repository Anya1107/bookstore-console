package by.bookstore.consoleapp.validator;

import by.bookstore.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidatorImpl implements BookValidator {

    @Override
    public boolean validBook(Book book) {
        if (book == null) return false;
        return book.getTitle().length() > 2 && book.getPrice() > 0;
    }


    @Override
    public boolean validBookTitle(String title) {
        return title.length()>2;
    }


    @Override
    public boolean validBookPrice(int price) {
        return price>0;
    }
}
