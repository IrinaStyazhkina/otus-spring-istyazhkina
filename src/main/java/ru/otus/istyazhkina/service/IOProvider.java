package ru.otus.istyazhkina.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;

@Component
public class IOProvider {

    public PrintStream getPrintStream() {
        return System.out;
    }

    public InputStream getInputStream() {
        return System.in;
    }
}
