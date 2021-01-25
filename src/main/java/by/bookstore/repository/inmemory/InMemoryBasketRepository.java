package by.bookstore.repository.inmemory;

import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.entity.Store;
import by.bookstore.repository.BasketRepository;
import by.bookstore.repository.file.DB;
import by.bookstore.repository.file.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class InMemoryBasketRepository implements BasketRepository {
    private DB db = new InMemoryDB();
    private static List<Basket> baskets;

    {
        baskets=db.read(Basket.class);
    }

    @Override
    public void addBook(Basket basket, Book book, Store store) {
        int lastBasketId = db.getLastId(Basket.class);
        basket.setId(++lastBasketId);
        db.setId(lastBasketId,Basket.class);
        baskets.add(basket);
        db.write(baskets,Basket.class);
    }
}


