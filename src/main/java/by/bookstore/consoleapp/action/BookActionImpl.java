package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.consoleapp.validator.BookValidator;
import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.service.AuthorService;
import by.bookstore.service.BookService;

public class BookActionImpl implements BookAction {

    private Reader reader;
    private Writer writer;
    private BookService bookService;
    private BookValidator validator;
    private AuthorService authorService;

    public BookActionImpl(Reader reader, Writer writer, BookService bookService, BookValidator validator, AuthorService authorService) {
        this.reader = reader;
        this.writer = writer;
        this.bookService = bookService;
        this.validator = validator;
        this.authorService = authorService;
    }

    @Override
    public void createBook() {
        writer.write("Введите название книги:");
        String name=reader.read();
        if(!checkBookName(name)){
            writer.write("Такая книга уже существует!");
            return;
        }
        writer.write("Выберите автора из списка:");
        Author author = getAuthorInList();
        writer.write("Введите цену:");
        int price=reader.readInt();
        writer.write("Введите описание:");
        String description=reader.read();
        Book book=new Book(name,author,price,description);
        if(validator.validBook(book)){
            bookService.add(book);
            return;
        }
        writer.write("Ввели не те данные.");
    }

    private boolean checkBookName(String bookName){
        return bookService.findByTitle(bookName)==null;
    }

    private Author getAuthorInList() {
        Author[] all = authorService.findAll();
        int count = 0;
        for (Author author : all) {
            if (author == null) break;
            writer.write(count + " " +author.getName());
            count++;
        }
        int i = reader.readInt();
        return all[i];
    }

    private int getBookPrice() {
        writer.write("Введите цену:");
        int price=reader.readInt();
        if(validator.validBookPrice(price)){
            return price;
        }
        return 0;
    }

    private String getBookTitle() {
        writer.write("Введите название:");
        String title=reader.read();
        if(validator.validBookTitle(title)){
            return title;
        }
        return "";
    }


    @Override
    public void delete() {
        writer.write("Введите ID для удаления:");
        int id = reader.readInt();
        bookService.delete(id);
    }

    @Override
    public void findAll() {
        int count=0;
        for(Book book:bookService.findAll()){
            if(book==null){
                writer.write("Список пуст!");
                break;
            }
            writer.write("Список книг на складе:");
            writer.write(count+" "+book.getTitle());
            count++;
        }
    }


    @Override
    public void findById() {
        writer.write("Введите Id для поиска:");
        int id=reader.readInt();
        writer.write(bookService.findById(id).getTitle());
    }

    @Override
    public void findByTitle() {
        writer.write("Введите название для поиска:");
        String title=reader.read();
        Book book = bookService.findByTitle(title);
        if(book!=null){
            writer.write("Такая книга есть в наличии.");
            choiseBook(book);
            return;
        }
        writer.write("Такой книги нет!");
    }

    @Override
    public void countOfBooks() {
        Book[] books = bookService.findAll();
        writer.write("В наличии на складе:");
        for (Book book : books) {
            if(book==null) break;
            writer.write(book.getTitle());
        }
    }

    @Override
    public void findByAuthorName() {
        writer.write("Введите имя автора:");
        String authorName=reader.read();
        if(authorService.findByName(authorName)==null){
            writer.write("Такого автора не существует!");
            return;
        }
        Author author = authorService.findByName(authorName);
        Book[] byAuthorName = bookService.findByAuthorName(author);
        for (int i = 0; i < byAuthorName.length; i++) {
            writer.write(i+ " "+ byAuthorName[i].getTitle());
        }
        writer.write("Выберите номер книги из списка:");
        int x=reader.readInt();
        Book book1 = byAuthorName[x];
        choiseBook(book1);
    }

    private void choiseBook(Book book){
        writer.write("Описание:"+ book.getDescription());
        writer.write("Название:"+book.getTitle());
        writer.write("Автор:"+book.getAuthor());
        writer.write("Цена:"+ book.getPrice());
        writer.write("Выберите действие: \n 1 - Добавить в корзину \n 2 - Выход");
        switch (reader.readInt()){
            case 1:
                addBookToBasket(book);
            case 2:
                return;
                default:
                    writer.write("Ввели не ту операцию!");
        }
    }

    private void addBookToBasket(Book book) {
        Book[] books = ConsoleApplicationImpl.activeSession.getBasket().getBooks();
        for (int i = 0; i <books.length ; i++) {
            if(books[i]==null){
                books[i]=book;
                break;
            }
        }
    }

   public void deleteBookInBasket(){
        Book[] books = ConsoleApplicationImpl.activeSession.getBasket().getBooks();
        for (int i = 0; i <books.length ; i++) {
            if(books[i]==null) break;
            writer.write(i+" "+ books[i].getTitle());
        }
        writer.write("Выберите книгу, которую хотите удалить:");
        int x=reader.readInt();
        if (books.length - 1 - x >= 0) System.arraycopy(books, x + 1, books, x, books.length - 1 - x);
    }
}

