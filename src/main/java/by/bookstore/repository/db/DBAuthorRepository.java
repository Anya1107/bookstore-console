package by.bookstore.repository.db;

import by.bookstore.entity.Author;
import by.bookstore.repository.AuthorRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAuthorRepository extends DBAbstractRepository implements AuthorRepository {
    private static final String INSERT_AUTHOR = "insert into authors values (default,?)";
    private static final String DELETE_AUTHOR = "delete from authors where id=?";
    private static final String SELECT_BY_NAME = "select * from authors where name=?";
    private static final String SELECT_BY_ID = "select * from authors where id = ?";
    private static final String SELECT_ALL = "select * from authors";

    @Override
    public void add(Author author) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_AUTHOR);
            statement.setString(1, author.getName());
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Author findByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            String name2 = resultSet.getString(2);
            Author author = new Author(name2, id);
            statement.close();
            return author;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Author findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id2 = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Author author = new Author(name, id2);
            statement.close();
            return author;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Author[] findAll() {
        try {
            List<Author> authorList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Author author = new Author(name, id);
                authorList.add(author);
            }
            statement.close();
            return authorList.toArray(new Author[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
