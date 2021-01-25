package by.bookstore.consoleapp.validator;

import by.bookstore.entity.Book;

public interface BookValidator {
    boolean validBook(Book book);
    boolean validBookTitle(String title);
    boolean validBookPrice(int price);
}
