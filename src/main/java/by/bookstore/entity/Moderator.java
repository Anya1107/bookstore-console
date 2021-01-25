package by.bookstore.entity;

import java.io.Serializable;

public class Moderator extends User implements Serializable {
    private Store store;

    public Moderator(String firstName, String lastName, String email, String password, Store store) {

        this.store = store;
    }

    public Moderator(){}

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
