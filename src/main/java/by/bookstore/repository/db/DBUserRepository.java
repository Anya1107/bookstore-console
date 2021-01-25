package by.bookstore.repository.db;

import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUserRepository extends DBAbstractRepository implements UserRepository {

    private static final String INSERT = "insert into users u values (default,?,?,?,?,?)";
    private static final String SELECT_ROLE = "select * from roles where role = ?";
    private static final String DELETE = "delete from users where id = ?";
    private static final String SELECT_BY_ID = "select * from users u join roles r on u.role_id=r.id where u.id = ?";
    private static final String SELECT_BY_EMAIL = "select * from users u join roles r on u.role_id=r.id where u.email = ?";
    private static final String SELECT_ALL="select * from users u join roles r on u.role_id=r.id";
    private static final String UPDATE_FIRSTNAME="update users u set first_name = ? where u.id  =?";
    private static final String UPDATE_LASTNAME="update users u set last_name = ? where u.id = ?";
    private static final String UPDATE_PASSWORD="update users u set password = ? where u.id = ?";
    private static final String DELETE_BY_EMAIL="delete from users where email = ?";

    @Override
    public void add(User user) {
        try {
            String name = user.getRole().name();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int roleId = resultSet.getInt(1);

            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, roleId);
            statement.execute();

            resultSet.close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public User findByEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email1 = resultSet.getString(4);
            String password = resultSet.getString(5);
            String role = resultSet.getString(8);

            User user = new User(userId, firstName, lastName, email1, password, Role.valueOf(role));

            resultSet.close();
            statement.close();

            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email1 = resultSet.getString(4);
            String password = resultSet.getString(5);
            String role = resultSet.getString(8);

            User user = new User(userId, firstName, lastName, email1, password, Role.valueOf(role));

            resultSet.close();
            statement.close();

            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User[] findAll() {
        List<User> users=new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email1 = resultSet.getString(4);
                String password = resultSet.getString(5);
                String role = resultSet.getString(8);

                User user = new User(userId, firstName, lastName, email1, password, Role.valueOf(role));
                users.add(user);
            }

            resultSet.close();
            statement.close();

            return users.toArray(new User[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFirstName(String firstName, int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(UPDATE_FIRSTNAME);
            statement.setString(1,firstName);
            statement.setInt(2,id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateLastName(String lastName, int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(UPDATE_LASTNAME);
            statement.setString(1,lastName);
            statement.setInt(2,id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String password, int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(UPDATE_PASSWORD);
            statement.setString(1,password);
            statement.setInt(2,id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteByEmail(String email) {
        try {
            PreparedStatement statement=connection.prepareStatement(DELETE_BY_EMAIL);
            statement.setString(1,email);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
