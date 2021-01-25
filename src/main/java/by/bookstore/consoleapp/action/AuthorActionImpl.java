package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.consoleapp.validator.AuthorValidator;
import by.bookstore.entity.Author;
import by.bookstore.service.AuthorService;

public class AuthorActionImpl implements AuthorAction {

    private AuthorService authorService;
    private Writer writer;
    private Reader reader;
    private AuthorValidator authorValidator;

    public AuthorActionImpl(AuthorService authorService, Writer writer, Reader reader, AuthorValidator authorValidator) {
        this.authorService = authorService;
        this.writer = writer;
        this.reader = reader;
        this.authorValidator = authorValidator;
    }

    @Override
    public void add() {
        writer.write("Введите автора:");
        String name = reader.read();
        if (!checkAuthorName(name)){
            writer.write("Такой автор уже существует!");
            return;
        }
        Author author = new Author(name);
        if (authorValidator.valid(author)) {
            authorService.add(author);
            return;
        }
        writer.write("Ввели не те данные.");
    }

    private boolean checkAuthorName(String authorName){
        return authorService.findByName(authorName)==null;
    }

    @Override
    public void delete() {
        writer.write("Введите ID:");
        int id=reader.readInt();
        authorService.delete(id);
    }

    @Override
    public void findById() {
        writer.write("Введите Id для поиска:");
        int id=reader.readInt();
        writer.write(authorService.findById(id).getName());
    }

    @Override
    public void findByName() {
        writer.write("Введите имя для поиска:");
        String name=reader.read();
        writer.write(String.valueOf(authorService.findByName(name).getId()));
    }

    @Override
    public void findAll() {
        int count=0;
        for(Author author:authorService.findAll()){
         if(author==null){
             writer.write("Список пуст!");
             break;
         }
         writer.write("Список авторов:");
         writer.write(count+" "+author.getName());
         count++;
        }
    }
}
