package by.bookstore.consoleapp.action;

import by.bookstore.consoleapp.util.Reader;
import by.bookstore.consoleapp.util.Writer;
import by.bookstore.consoleapp.validator.AddressValidator;
import by.bookstore.entity.Address;
import by.bookstore.service.AddressService;


public class AddressActionImpl implements AddressAction {

    private Writer writer;
    private Reader reader;
    private AddressService addressService;
    private AddressValidator addressValidator;

    public AddressActionImpl(Writer writer, Reader reader, AddressService addressService, AddressValidator addressValidator) {
        this.writer = writer;
        this.reader = reader;
        this.addressService = addressService;
        this.addressValidator = addressValidator;
    }

    @Override
    public void add() {
        writer.write("Введите адрес:");
        String addressName=reader.read();
        if(!checkAddressName(addressName)){
            writer.write("Такой адрес уже существует!");
            return;
        }
        Address address=new Address(addressName);
        if(addressValidator.validAddressName(address)){
        addressService.add(address);
        return;
        }
        writer.write("Ввели не те данные.");
    }


    private boolean checkAddressName(String name){
        return addressService.findByName(name)==null;
    }

    @Override
    public void delete() {
        writer.write("Введите Id:");
        int id=reader.readInt();
        addressService.delete(id);
    }

    @Override
    public void findById() {
        writer.write("Введите Id:");
        int id=reader.readInt();
        writer.write(addressService.findById(id).getAddress());
    }

    @Override
    public void findAll() {
        Address[] all = addressService.findAll();
        int count=0;
        for (Address address : all) {
            if (address == null) {
                writer.write("Список пуст!");
                break;
            }
            writer.write("Список адресов:");
            writer.write(count +" " +address.getAddress());
            count++;
        }
    }
}
