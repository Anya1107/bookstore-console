package by.bookstore.repository.db;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.repository.BookRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBBookRepository extends DBAbstractRepository implements BookRepository {

    // TODO: 19.09.2020 StoreRepository
    // TODO: 19.09.2020 Refactoring

    private static final String INSERT = "insert into books values (default,?,?,?,?)";
    private static final String DELETE_BY_ID = "delete from books where id = ?";
    private static final String SELECT_BY_ID = "select * from books b join authors a on b.author_id=a.id where b.id = ?";
     // TODO: 19.09.2020 join table
    private static final String SELECT_ALL_WITH_JOIN="select * from books b join authors a on b.author_id=a.id";
    private static final String SELECT_BY_TITLE_WITH_JOIN="select * from books b join authors a on b.author_id=a.id where title = ?";
    private static final String SELECT_BOOK_BY_AUTHOR_NAME_WITH_JOIN="select * from books b join authors a on b.author_id=a.id where name = ?";

    public static void main(String[] args) {
        DBBookRepository dbBookRepository = new DBBookRepository();
//        dbBookRepository.add(new Book("test", new Author("test", 1), 22, "descr"));
//        dbBookRepository.add(new Book("test2", new Author("test", 1), 22, "descr"));
//        dbBookRepository.add(new Book("test3", new Author("test", 1), 22, "descr"));
//        dbBookRepository.add(new Book("test4", new Author("test", 2), 22, "descr"));
//        dbBookRepository.add(new Book("test5", new Author("test", 2), 22, "descr"));

       // System.out.println(Arrays.toString(dbBookRepository.findAll()));
//        System.out.println(Arrays.toString(dbBookRepository.findByAuthorName(new Author("sad", 1))));
         // System.out.println(dbBookRepository.findByTitle("test4"));
//        System.out.println(dbBookRepository.findById(4));
    //    dbBookRepository.delete(3);
       // System.out.println(Arrays.toString(dbBookRepository.findByAuthorName(new Author("test2"))));
        System.out.println(dbBookRepository.findById(4));
    }

    @Override
    public void add(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPrice());
            statement.setString(3, book.getDescription());
            statement.setInt(4, book.getAuthor().getId());
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Book findByTitle(String title) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_TITLE_WITH_JOIN);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Book book=resultBook(resultSet);

            statement.close();
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findById(int id1) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id1);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Book book=resultBook(resultSet);

            statement.close();
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    @Override
    public Book[] findAll() {
        try {
            List<Book> books = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WITH_JOIN);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book=resultBook(resultSet);
                books.add(book);
            }
            statement.close();
            return books.toArray(new Book[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public Book[] findByAuthorName(Author author) {
        try {
            List<Book> books=new ArrayList<>();
            PreparedStatement statement=connection.prepareStatement(SELECT_BOOK_BY_AUTHOR_NAME_WITH_JOIN);
            statement.setString(1,author.getName());
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Book book=resultBook(resultSet);
                books.add(book);
            }

            return books.toArray(new Book[0]);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Book resultBook(ResultSet resultSet){
        try {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            int price = resultSet.getInt(3);
            String description = resultSet.getString(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);


            Author author = new Author(authorName, authorId);
            Book book = new Book(id, title, author, price, description);
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
