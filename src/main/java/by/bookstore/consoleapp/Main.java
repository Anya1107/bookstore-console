package by.bookstore.consoleapp;

import by.bookstore.consoleapp.action.*;
import by.bookstore.consoleapp.util.ConsoleReader;
import by.bookstore.consoleapp.util.ConsoleWriter;
import by.bookstore.consoleapp.validator.*;
import by.bookstore.repository.*;
import by.bookstore.repository.file.*;
import by.bookstore.repository.inmemory.*;
import by.bookstore.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.bookstore")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Main.class);
        ConsoleApplication consoleApplicationImpl = (ConsoleApplication) context.getBean("consoleApplication");
        consoleApplicationImpl.run();

    }

    @Bean
    public UserService userService(@Qualifier("inMemoryUserRepository")  UserRepository userRepository){
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public AddressService addressService(@Qualifier("inMemoryAddressRepository") AddressRepository addressRepository){
        return new AddressServiceImpl(addressRepository);
    }

    @Bean
    public AuthorService authorService(@Qualifier("inMemoryAuthorRepository") AuthorRepository authorRepository){
        return new AuthorServiceImpl(authorRepository);
    }

    @Bean
    public BookService bookService(@Qualifier("inMemoryBookRepository") BookRepository bookRepository){
        return new BookServiceImpl(bookRepository);
    }

    @Bean
    public CityService cityService(@Qualifier("inMemoryCityRepository") CityRepository cityRepository){
        return new CityServiceImpl(cityRepository);
    }

    @Bean
    public ModerService moderService(@Qualifier("inMemoryOrderRepository") OrderRepository orderRepository){
        return new ModerServiceImpl(orderRepository);
    }

    @Bean
    public OrderService orderService(@Qualifier("inMemoryOrderRepository") OrderRepository orderRepository){
        return new OrderServiceImpl(orderRepository);
    }

    @Bean
    public StoreService storeService(@Qualifier("inMemoryStoreRepository") StoreRepository storeRepository){
        return new StoreServiceImpl(storeRepository);
    }

    @Bean
    public InMemoryAddressRepository inMemoryAddressRepository(){
        return new InMemoryAddressRepository();
    }

    @Bean
    public InMemoryAuthorRepository inMemoryAuthorRepository(){
        return new InMemoryAuthorRepository();
    }

    @Bean
    public InMemoryBasketRepository inMemoryBasketRepository(){
        return new InMemoryBasketRepository();
    }

    @Bean
    public InMemoryBookRepository inMemoryBookRepository(){
        return new InMemoryBookRepository();
    }

    @Bean
    public InMemoryCityRepository inMemoryCityRepository(){
        return new InMemoryCityRepository();
    }

    @Bean
    public InMemoryOrderRepository inMemoryOrderRepository(){
        return new InMemoryOrderRepository();
    }

    @Bean
    public InMemoryStoreRepository inMemoryStoreRepository(){
        return new InMemoryStoreRepository();
    }

    @Bean
    public InMemoryUserRepository inMemoryUserRepository(){
        return new InMemoryUserRepository();
    }

    @Bean
    public FileAddressRepository fileAddressRepository(){
        return new FileAddressRepository();
    }

    @Bean
    public FileAuthorRepository fileAuthorRepository(){
        return new FileAuthorRepository();
    }

    @Bean
    public FileBookRepository fileBookRepository(){
        return new FileBookRepository();
    }

    @Bean
    public FileCityRepository fileCityRepository(){
        return new FileCityRepository();
    }

    @Bean
    public FileOrderRepository fileOrderRepository(){
        return new FileOrderRepository();
    }

    @Bean
    public FileStoreRepository fileStoreRepository(){
        return new FileStoreRepository();
    }

    @Bean
    public FileUserRepository fileUserRepository(){
        return new FileUserRepository();
    }

    @Bean
    public ConsoleReader consoleReader(){
        return new ConsoleReader();
    }

    @Bean
    public ConsoleWriter consoleWriter(){
        return new ConsoleWriter();
    }

    @Bean
    public AddressValidator addressValidator(){
        return new AddressValidator();
    }

    @Bean
    public AuthorValidator authorValidator(){
        return new AuthorValidatorImpl();
    }

    @Bean
    public BookValidator bookValidator(){
        return new BookValidatorImpl();
    }

    @Bean
    public CityValidator cityValidator(){
        return new CityValidatorImpl();
    }

    @Bean
    public StoreValidator storeValidator(){
        return new StoreValidatorImpl();
    }

    @Bean
    public UserValidator userValidator(){
        return new UserValidatorImpl();
    }

    @Bean
    public AddressAction addressAction(ConsoleWriter writer,ConsoleReader reader, AddressService addressService, AddressValidator addressValidator){
        return new AddressActionImpl(writer,reader,addressService,addressValidator);
    }

    @Bean
    public AdminAction adminAction(ConsoleWriter writer,ConsoleReader reader, UserService userService, StoreService storeService){
        return new AdminActionImpl(writer,reader,userService,storeService);
    }

    @Bean
    public AuthorAction authorAction(AuthorService authorService, ConsoleReader reader, ConsoleWriter writer, AuthorValidator authorValidator){
        return new AuthorActionImpl(authorService,writer,reader,authorValidator);
    }

    @Bean
    public BasketAction basketAction(){
        return new BasketActionImpl();
    }

    @Bean
    public BookAction bookAction(ConsoleReader reader, ConsoleWriter writer, BookService bookService, BookValidator bookValidator, AuthorService authorService){
        return new BookActionImpl(reader, writer, bookService, bookValidator,authorService);
    }

    @Bean
    public CityAction cityAction(CityService cityService, ConsoleWriter writer, ConsoleReader reader, CityValidator cityValidator){
        return new CityActionImpl(cityService, writer, reader, cityValidator);
    }

    @Bean
    public ModerAction moderAction(ConsoleReader reader, ConsoleWriter writer, OrderService orderService){
        return new ModerActionImpl(writer, reader, orderService);
    }

    @Bean
    public OrderAction orderAction(ConsoleWriter writer, ConsoleReader reader, OrderService orderService, StoreService storeService){
        return new OrderActionImpl(reader,writer,storeService,orderService);
    }

    @Bean
    public StoreAction storeAction(ConsoleReader reader, ConsoleWriter writer, CityService cityService, AddressService addressService, BookService bookService, StoreValidator storeValidator, StoreService storeService){
        return new StoreActionImpl(writer, reader, cityService, bookService, addressService, storeValidator, storeService);
    }

    @Bean
    public UserAction userAction(UserService userService, ConsoleWriter writer, ConsoleReader reader, UserValidator userValidator, OrderService orderService, StoreService storeService){
        return new UserActionImpl(userService, writer, reader, userValidator, orderService, storeService);
    }

    @Bean
    public ConsoleApplication consoleApplication(ConsoleWriter writer,
                                                 ConsoleReader reader,
                                                 AddressAction addressAction,
                                                 AuthorAction authorAction,
                                                 CityAction cityAction,
                                                 BookAction bookAction,
                                                 BasketAction basketAction,
                                                 ModerAction moderAction,
                                                 StoreAction storeAction,
                                                 OrderAction orderAction,
                                                 AdminAction adminAction,
                                                 UserAction userAction){
        return new ConsoleApplicationImpl(writer, reader, authorAction, bookAction, cityAction, storeAction, addressAction, userAction, adminAction, orderAction,moderAction);
    }
}
