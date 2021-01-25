package by.bookstore.entity;

import java.io.Serializable;
import java.util.Objects;

public class Store implements Serializable {
    private int id;
    private String name;
    private Address address;
    private City city;

    public Store(){
    }

    public Store(int id, String name, Address address, City city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Store(String name, City city, Address address) {
        this.name=name;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }



    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", address=" +address +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(name, store.name) &&
                Objects.equals(address, store.address) &&
                Objects.equals(city, store.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, city);
    }
}
