package by.bookstore.repository.file;

import by.bookstore.entity.City;
import by.bookstore.repository.CityRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileCityRepository implements CityRepository {
    private DB db = new FileDB();
    private List<City> temp;


    {
        temp=db.read(City.class);
    }

    public List<City> getCityTemp() {
        return temp;
    }

    @Override
    public void add(City city) {
        int lastCityId = db.getLastId(City.class);
        city.setId(++lastCityId);
        db.setId(lastCityId,City.class);
        temp.add(city);
        db.write(temp,City.class);
    }

    @Override
    public void delete(String name) {
        for(City city:temp){
            if(city==null) break;
            if(city.getName().equals(name)){
                temp.remove(name);
            }
        }
        db.write(temp,City.class);
    }

    @Override
    public City findByName(String name) {
        for(City city:temp){
            if(city.getName().equals(name)){
                return city;
            }
        }
        return null;
    }

    @Override
    public City[] findAll() {
        return temp.toArray(new City[0]);
    }
}
