package by.bookstore.repository.inmemory;

import by.bookstore.entity.Author;
import by.bookstore.repository.AuthorRepository;
import by.bookstore.repository.file.DB;
import by.bookstore.repository.file.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryAuthorRepository implements AuthorRepository {
    private DB db=new InMemoryDB();
    private List<Author> authors;

    {
        authors = db.read(Author.class);
    }


    @Override
    public void add(Author author) {
        int lastAuthorId = db.getLastId(Author.class);
        author.setId(++lastAuthorId);
        db.setId(lastAuthorId,Author.class);
        authors.add(author);
        db.write(authors,Author.class);
    }

    @Override
    public void delete(int id) {
        for(Author author:authors){
            if(author.getId()==id){
                authors.remove(author);
            }
        }
        db.write(authors,Author.class);
    }

    @Override
    public Author findByName(String name) {
        for (Author author : authors) {
            if (author == null) break;
            if (author.getName().equals(name)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public Author findById(int id) {
        for (Author author : authors) {
            if (author == null) break;
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }


    @Override
    public Author[] findAll() {
        return authors.toArray(new Author[0]);
    }
}
