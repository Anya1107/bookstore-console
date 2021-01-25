package by.bookstore.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private Store store;
    private User user;
    private Book[] books;
    private Status status = Status.ACTIVE;
    private boolean isDelivery;
    private Address address;
    private Date date = Calendar.getInstance().getTime();


    public Order(int id, Store store, User user, Book[] books, Status status, boolean isDelivery, Date date) {
        this.id = id;
        this.store = store;
        this.user = user;
        this.books = books;
        this.status = status;
        this.isDelivery = isDelivery;
        this.date = date;
    }

    public Order(int id, User user, Book[] books, Status status, boolean isDelivery, Address address, Date date) {
        this.id = id;
        this.user = user;
        this.books = books;
        this.status = status;
        this.isDelivery = isDelivery;
        this.address = address;
        this.date = date;
    }

    public Order(Store store, User user, Book[] books) {
        this.store = store;
        this.user = user;
        this.books = books;
    }

    public Order(User user, Book[] books, Address address) {
        this.user = user;
        this.books = books;
        this.isDelivery = true;
        this.address = address;
    }



    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return isDelivery == order.isDelivery &&
                Objects.equals(store, order.store) &&
                Objects.equals(user, order.user) &&
                Arrays.equals(books, order.books) &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(store, user, isDelivery, address);
        result = 31 * result + Arrays.hashCode(books);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", store=" + store +
                ", user=" + user +
                ", books=" + Arrays.toString(books) +
                ", status=" + status +
                ", isDelivery=" + isDelivery +
                ", address=" + address +
                ", date=" + date +
                '}';
    }
}
