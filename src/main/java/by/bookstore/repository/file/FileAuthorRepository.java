package by.bookstore.repository.file;

import by.bookstore.entity.Author;
import by.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileAuthorRepository implements AuthorRepository {
    private DB db= new FileDB();
    private List<Author> temp;

    {
        temp=db.read(Author.class);
    }

    public List<Author> getAuthorTemp() {
        return temp;
    }

    @Override
    public void add(Author author) {
        int lastAuthorId = db.getLastId(Author.class);
        author.setId(++lastAuthorId);
        db.setId(lastAuthorId,Author.class);
        temp.add(author);
        db.write(temp,Author.class);
    }

    @Override
    public void delete(int id) {
        for(Author author:temp){
            if(author==null) break;
            if(author.getId()==id){
                temp.remove(author);
            }
        }
       db.write(temp,Author.class);
    }

    @Override
    public Author findByName(String name) {
        for(Author author:temp){
            if(author.getName().equals(name)){
                return author;
            }
        }
        return null;
    }

    @Override
    public Author findById(int id) {
        for(Author author:temp){
            if(author.getId()==id){
                return author;
            }
        }
        return null;
    }

    @Override
    public Author[] findAll() {
        return temp.toArray(new Author[0]);
    }
}
