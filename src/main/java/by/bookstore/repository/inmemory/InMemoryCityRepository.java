package by.bookstore.repository.inmemory;

import by.bookstore.entity.City;
import by.bookstore.repository.CityRepository;
import by.bookstore.repository.file.DB;
import by.bookstore.repository.file.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryCityRepository implements CityRepository {
    private DB db = new InMemoryDB();
    private List<City> cities;

    {
        cities=db.read(City.class);
    }

    private static CityRepository cityRepository;


    @Override
    public void add(City city) {
        int lastCityId = db.getLastId(City.class);
        city.setId(++lastCityId);
        db.setId(lastCityId,City.class);
        cities.add(city);
        db.write(cities,City.class);
    }

    @Override
    public void delete(String name) {
        for(City city:cities){
            if(city.getName().equals(name)){
                cities.remove(city);
            }
        }
        db.write(cities,City.class);
    }

    @Override
    public City findByName(String name) {
        for (City cities : cities) {
            if (cities == null) break;
            if (cities.getName().equals(name)) {
                return cities;
            }
        }
        return null;
    }

    @Override
    public City[] findAll() {
        return cities.toArray(new City[0]);
    }
}
