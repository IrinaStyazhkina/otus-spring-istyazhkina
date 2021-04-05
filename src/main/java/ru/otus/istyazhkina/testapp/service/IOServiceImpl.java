package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream out;
    private final Scanner scanner;

    public IOServiceImpl(IOProvider ioProvider) {
        this.out = ioProvider.getPrintStream();
        this.scanner = new Scanner(ioProvider.getInputStream());
    }

    @Override
    public void write(String text) {
        out.println(text);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public int readIntInRange(int minValue, int maxValue) {
        int answer = 0;
        do {
            try {
                int input = Integer.parseInt(read());
                if (input < minValue || input > maxValue) {
                    throw new InputMismatchException("Available answers are: 1, 2 or 3");
                }
                answer = input;
            } catch (Exception e) {
                write("Invalid data. Please write down the number of chosen answer!");
            }
        }
        while (answer == 0);
        return answer;
    }
}
