package by.bookstore.repository.db;

import by.bookstore.entity.Address;
import by.bookstore.repository.AddressRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAddressesRepository  extends DBAbstractRepository implements AddressRepository {

    private static final String INSERT_ADDRESS="insert into addresses values (default,?)";
    private static final String DELETE_BY_ID="delete from addresses where id = ?";
    private static final String SELECT_BY_NAME="select * from addresses where address = ?";
    private static final String SELECT_BY_ID="select * from addresses where id = ?";
    private static final String SELECT_ALL="select * from addresses";

    @Override
    public void add(Address address) {
        try {
            PreparedStatement statement=connection.prepareStatement(INSERT_ADDRESS);
            statement.setString(1,address.getAddress());
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Address findById(int id) {
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            int id2 = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Address address=new Address(id2,name);
            statement.close();
            return address;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Address findByName(String name) {
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_BY_NAME);
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            String address2 = resultSet.getString(2);
            Address address=new Address(id,address2);
            statement.close();
            return address;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Address[] findAll() {
        try {
            List<Address> addressesList= new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
               Address address=new Address(id,name);
                addressesList.add(address);
            }
            statement.close();
            return addressesList.toArray(new Address[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
