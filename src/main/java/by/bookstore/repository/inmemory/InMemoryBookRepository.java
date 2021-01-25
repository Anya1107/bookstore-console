package by.bookstore.repository.inmemory;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.repository.BookRepository;
import by.bookstore.repository.file.DB;
import by.bookstore.repository.file.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryBookRepository implements BookRepository {
    private DB db = new InMemoryDB();
    private List<Book> books;

    {
        books=db.read(Book.class);
    }

    @Override
    public void add(Book book) {
        int lastBookId = db.getLastId(Book.class);
        book.setId(++lastBookId);
        db.setId(lastBookId,Book.class);
        books.add(book);
        db.write(books,Book.class);
    }

    @Override
    public void delete(int id) {
        for(Book book:books){
            if(book.getId()==id){
                books.remove(book);
            }
        }
        db.write(books,Book.class);
    }

    @Override
    public Book findByTitle(String title) {
        for (Book value : books) {
            if (value == null) break;
            if (value.getTitle().equals(title)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public Book findById(int id) {
        for (Book value : books) {
            if (value == null) break;
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }

    @Override
    public Book[] findAll() {
        return books.toArray(new Book[0]);
    }

    @Override
    public Book[] findByAuthorName(Author author) {
        List<Book> book1=new ArrayList<>();

       for(Book book:books){
           if(book.getAuthor().getName().equals(author.getName())){
               book1.add(book);
           }
       }
        return book1.toArray(new Book[0]);
    }
}
