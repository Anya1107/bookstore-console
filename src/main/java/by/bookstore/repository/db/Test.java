package by.bookstore.repository.db;

import java.sql.*;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
      //  test.save(new User("new", "new"));
       // test.deleteById(3);
        //test.deleteByFirstName("newTest");
       // test.updateFirstNameById("Test",6);
       // test.updateLastNameById("Test",6);
       // test.selectByLastName("Test");
       // test.selectAll();
        test.join();
    }

    public void join(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement statement=connection.prepareStatement("select * from users u join telephones t on u.id=t.id");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int userId = resultSet.getInt(1);
                String userFirstName = resultSet.getString(2);
                String userLastName = resultSet.getString(3);
                int number = resultSet.getInt(5);
                System.out.println(userId+" "+userFirstName+" "+userLastName+" "+number);
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void save(User user){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement statement = connection.prepareStatement("insert into users values (default, ?, ?)");
            statement.setString(1, user.firstName);
            statement.setString(2, user.lastName);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteById(int id){
        try{
            Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement statement=connection.prepareStatement("delete from users where id = ?");
            statement.setInt(1,id);
            statement.execute();
            statement.close();
            connection.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteByFirstName(String firstName){
        try{
            Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement statement=connection.prepareStatement("delete from users where first_name=?");
            statement.setString(1,firstName);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


    public void updateFirstNameById(String firstName, int id){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement statement=connection.prepareStatement("update users set first_name=? where id=?");
            statement.setString(1,firstName);
            statement.setInt(2,id);
            statement.executeUpdate();
            //System.out.println(statement.executeUpdate());
            statement.close();
            connection.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void updateLastNameById(String lastName, int id){
        try{
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement statement=connection.prepareStatement("update users set last_name=? where id=?");
            statement.setString(1,lastName);
            statement.setInt(2,id);
            statement.executeUpdate();
            statement.close();
            connection.close();

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

    public void selectAll(){
        try{
            Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from users");
            while(rs.next()){
                System.out.println(rs.getString(2)+" "+rs.getString(3));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static class User{
        private int id;
        private String firstName;
        private String lastName;

        public User() {
        }

        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public User(int id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return Objects.equals(firstName, user.firstName) &&
                    Objects.equals(lastName, user.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }
}
