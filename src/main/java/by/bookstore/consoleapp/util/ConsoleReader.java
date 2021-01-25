package by.bookstore.consoleapp.util;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader implements Reader{

    private Scanner scan=new Scanner(System.in);

    @Override
    public String read() {
        return scan.next();
    }

    @Override
    public int readInt() {
        return scan.nextInt();
    }
}
