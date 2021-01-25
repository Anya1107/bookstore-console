package by.bookstore.repository.db;

import by.bookstore.entity.City;
import by.bookstore.repository.CityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBCityRepository extends DBAbstractRepository implements CityRepository {

    private static final String INSERT="insert into cities values (default, ?)";
    private static final String DELETE_BY_NAME="delete from cities where name = ?";
    private static final String SELECT_BY_NAME="select * from cities where name = ?";
    private static final String SELECT_ALL="select * from cities";

    public static void main(String[] args) {
        DBCityRepository dbCityRepository=new DBCityRepository();
       // dbCityRepository.add(new City("Minsk"));
       // dbCityRepository.add(new City("Test"));
       // dbCityRepository.delete("Test");
       // System.out.println(dbCityRepository.findByName("Minsk"));
        System.out.println(Arrays.toString(dbCityRepository.findAll()));
    }

    @Override
    public void add(City city) {
        try {
            PreparedStatement statement=connection.prepareStatement(INSERT);
            statement.setString(1,city.getName());
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String name) {
        try {
            PreparedStatement statement=connection.prepareStatement(DELETE_BY_NAME);
            statement.setString(1,name);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public City findByName(String name) {
        try {
            PreparedStatement statement=connection.prepareStatement(SELECT_BY_NAME);
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            String cityName = resultSet.getString(2);
            City city=new City(id,cityName);
            statement.close();
            return city;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public City[] findAll() {
        try {
            List<City> cities=new ArrayList<>();
            PreparedStatement statement=connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                City city=new City(id,name);
                cities.add(city);
            }
            statement.close();
            return cities.toArray(new City[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
