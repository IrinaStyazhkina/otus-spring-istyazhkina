package ru.otus.istyazhkina.exception;

public class ReadCsvException extends RuntimeException {

    public ReadCsvException() {}

    public ReadCsvException(String message) {
        super(message);
    }

    public ReadCsvException(String message, Throwable cause) {
        super(message, cause);
    }
}
