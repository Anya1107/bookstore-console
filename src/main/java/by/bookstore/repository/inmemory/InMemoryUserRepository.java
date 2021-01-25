package by.bookstore.repository.inmemory;

import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.repository.UserRepository;
import by.bookstore.repository.file.DB;
import by.bookstore.repository.file.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryUserRepository implements UserRepository {
    private DB db = new InMemoryDB();
    private static List<User> users;

    {users = db.read(User.class);}


    static {
        User admin = new User("Admin", "Admin", "admin@a.by", "12345", Role.ADMIN);
        User moderator = new User("User", "User", "moder@a.by", "12345", Role.USER);
    }

    @Override
    public void add(User user) {
        int lastUserId = db.getLastId(User.class);
        user.setId(++lastUserId);
        db.setId(lastUserId,User.class);
        users.add(user);
        db.write(users,User.class);
    }

    @Override
    public void delete(int id) {
        for(User user:users){
            if(user.getId()==id){
                users.remove(user);
            }
        }
        db.write(users,User.class);
    }

    @Override
    public User findByEmail(String email) {
        for(User user:users){
            if(user==null) break;
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findById(int id) {
       for(User user:users){
           if(user==null) break;
           if(user.getId()==id){
               return user;
           }
       }
       return null;
    }

    @Override
    public User[] findAll() {
        return users.toArray(new User[0]);
    }

    @Override
    public void updateFirstName(String firstName, int id) {
        for(User user:users){
            if(user==null) break;
            if(user.getId()==id){
                user.setFirstName(firstName);
                return;
            }
        }
    }

    @Override
    public void updateLastName(String lastName, int id) {
        for(User user:users){
            if(user==null) break;
            if(user.getId()==id){
                user.setLastName(lastName);
                return;
            }
        }
    }

    @Override
    public void updatePassword(String password, int id) {
        for(User user:users){
            if(user==null) break;
            if(user.getId()==id){
                user.setPassword(password);
                return;
            }
        }
    }

    @Override
    public void deleteByEmail(String email) {
        for(User user:users){
            if(user==null) break;
            if(user.getEmail().equals(email)){
                users.remove(user);
            }
        }
    }
}
