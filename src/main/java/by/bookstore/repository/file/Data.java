package by.bookstore.repository.file;

import by.bookstore.entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {
   private List<Address> addresses = new ArrayList<>();
   private List<Author> authors = new ArrayList<>();
   private List<Basket> baskets = new ArrayList<>();
   private List<Book> books = new ArrayList<>();
   private List<City> cities = new ArrayList<>();
   private List<Order> orders = new ArrayList<>();
   private List<Store> stores = new ArrayList<>();
   private List<User> users = new ArrayList<>();

   private int addressId;
   private int authorId;
   private int basketId;
   private int bookId;
   private int cityId;
   private int orderId;
   private int storeId;
   private int userId;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getBasketId() {
        return basketId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getCityId() {
        return cityId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
