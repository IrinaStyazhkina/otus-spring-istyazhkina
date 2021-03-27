package ru.otus.istyazhkina.service;

import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private PrintStream out;
    private Scanner scanner;

    public IOServiceImpl() {
        this.out = new PrintStream(System.out);
        this.scanner = new Scanner(System.in);
    }

    public void write(String text) {
        out.println(text);
    }

    public String read() {
        return scanner.nextLine();
    }
}
