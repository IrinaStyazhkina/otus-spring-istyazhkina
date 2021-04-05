package ru.otus.istyazhkina.testapp.exceptions;

public class QuestionReadException extends RuntimeException {

    public QuestionReadException() {
    }

    public QuestionReadException(String message) {
        super(message);
    }

    public QuestionReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
