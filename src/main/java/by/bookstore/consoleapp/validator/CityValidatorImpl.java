package by.bookstore.consoleapp.validator;

import by.bookstore.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityValidatorImpl implements CityValidator {

    public boolean validCityName(City city){
        return city.getName().length()>2;
    }
}
