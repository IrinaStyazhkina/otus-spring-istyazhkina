package ru.otus.istyazhkina.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream out;
    private final Scanner scanner;

    public IOServiceImpl(IOProvider ioProvider) {
        this.out = ioProvider.getPrintStream();
        this.scanner = new Scanner(ioProvider.getInputStream());
    }

    public void write(String text) {
        out.println(text);
    }

    public String read() {
        return scanner.nextLine();
    }
}
