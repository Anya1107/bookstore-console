package by.bookstore.consoleapp.action;

public interface BookAction {
    void createBook();
    void delete();
    void findAll();
    void findById();
    void findByTitle();
    void countOfBooks();
    void findByAuthorName();
    void deleteBookInBasket();
}
