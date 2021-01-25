package by.bookstore.service;

import by.bookstore.entity.City;
import by.bookstore.repository.CityRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(@Qualifier("inMemoryCityRepository") CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void add(City city) {
      cityRepository.add(city);
    }

    @Override
    public void delete(String name) {
       cityRepository.delete(name);
    }

    @Override
    public City findByName(String name) {
       return cityRepository.findByName(name);
    }

    @Override
    public City[] findAll() {
        return cityRepository.findAll();
    }
}
