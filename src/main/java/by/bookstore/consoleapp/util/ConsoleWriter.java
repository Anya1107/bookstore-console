package by.bookstore.consoleapp.util;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements Writer {

    @Override
    public void write(String s) {
        System.out.println(s);
    }
}
