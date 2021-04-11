package ru.otus.istyazhkina.testapp.service;

public interface IOService {

    void write(String text);

    String read();

    int readIntInRange(int minValue, int maxValue);
}
