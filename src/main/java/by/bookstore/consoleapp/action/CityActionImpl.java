package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.consoleapp.validator.CityValidator;
import by.bookstore.entity.City;
import by.bookstore.service.CityService;

public class CityActionImpl implements CityAction {

    private CityService cityService;
    private Writer writer;
    private Reader reader;
    private CityValidator cityValidator;

    public CityActionImpl(CityService cityService, Writer writer, Reader reader, CityValidator cityValidator) {
        this.cityService = cityService;
        this.writer = writer;
        this.reader = reader;
        this.cityValidator = cityValidator;
    }

    @Override
    public void add() {
        writer.write("Введите название города:");
        String name=reader.read();
        if(!checkCityName(name)){
            writer.write("Такой город уже существует!");
            return;

        }
        City city=new City(name);
        if(cityValidator.validCityName(city)){
            cityService.add(city);
            return;
        }
        writer.write("Ввели не те данные.");
    }

    private boolean checkCityName(String cityName){
        return cityService.findByName(cityName)==null;
    }

    @Override
    public void delete() {
        writer.write("Введите название города для удаления:");
        String name=reader.read();
        cityService.delete(name);
    }

    @Override
    public void findAll() {
        int count=0;
        for(City city:cityService.findAll()){
            if(city==null){
                writer.write("Список пуст!");
                break;
            }
            writer.write("Список городов:");
            writer.write(count+" "+city.getName());
            count++;
        }
    }

    @Override
    public void findByName() {
        writer.write("Введите имя для поиска:");
        String name=reader.read();
        cityService.findByName(name);
    }
}
