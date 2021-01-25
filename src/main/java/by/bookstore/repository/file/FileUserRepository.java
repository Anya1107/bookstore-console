package by.bookstore.repository.file;

import by.bookstore.entity.*;
import by.bookstore.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FileUserRepository implements UserRepository {
    private DB db = new FileDB();
    private List<User> temp;

    public static void main(String[] args) {
        FileAddressRepository fileAddressRepository = new FileAddressRepository();
//        fileAddressRepository.add(new Address("test1"));
//        fileAddressRepository.add(new Address("test2"));
        System.out.println(Arrays.toString(fileAddressRepository.findAll()));
        FileAuthorRepository fileAuthorRepository = new FileAuthorRepository();
//        fileAuthorRepository.add(new Author("test3"));
//        fileAuthorRepository.add(new Author("test4"));
        System.out.println(Arrays.toString(fileAuthorRepository.findAll()));
        FileBookRepository fileBookRepository = new FileBookRepository();
    //    fileBookRepository.add(new Book("test1",null,4500,null));
//        fileBookRepository.add(new Book("test2",null,4500,null));
        System.out.println(Arrays.toString(fileBookRepository.findAll()));
        FileCityRepository fileCityRepository = new FileCityRepository();
//        fileCityRepository.add(new City("test1"));
//        fileCityRepository.add(new City("test2"));
        System.out.println(Arrays.toString(fileCityRepository.findAll()));
        FileUserRepository fileUserRepository = new FileUserRepository();
//        fileUserRepository.add(new User());
//        fileUserRepository.add(new User());
        System.out.println(Arrays.toString(fileUserRepository.findAll()));
    }


    {
        temp = db.read(User.class);
    }

    public List<User> getUserTemp() {
        return temp;
    }

    @Override
    public void add(User user) {
        int lastUserId = db.getLastId(User.class);
        user.setId(++lastUserId);
        db.setId(lastUserId,User.class);
        temp.add(user);
        db.write(temp,User.class);
    }

    @Override
    public void delete(int id) {
        for(User user:temp){
            if(user==null) break;
            if(user.getId()==id){
                temp.remove(user);
            }
        }
        db.write(temp,User.class);
    }

    @Override
    public User findByEmail(String email) {
        for(User user:temp){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findById(int id) {
        for(User user:temp){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    @Override
    public User[] findAll() {
        return temp.toArray(new User[0]);
    }

    @Override
    public void updateFirstName(String firstName, int id) {
        for(User user:temp){
            if(user==null) break;
            if(user.getId()==id){
                user.setFirstName(firstName);
                return;
            }
        }
        db.write(temp,User.class);
    }

    @Override
    public void updateLastName(String lastName, int id) {
        for(User user:temp){
            if(user==null) break;
            if(user.getId()==id){
                user.setLastName(lastName);
                return;
            }
        }
        db.write(temp,User.class);
    }

    @Override
    public void updatePassword(String password, int id) {
        for(User user:temp){
            if(user==null) break;
            if(user.getId()==id){
                user.setPassword(password);
                return;
            }
        }
        db.write(temp,User.class);
    }

    @Override
    public void deleteByEmail(String email) {
        for(User user:temp){
            if(user==null) break;
            if(user.getEmail().equals(email)){
                temp.remove(user);
            }
        }
        db.write(temp,User.class);
    }
}
