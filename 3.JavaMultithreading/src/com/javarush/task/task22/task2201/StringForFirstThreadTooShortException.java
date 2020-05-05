package com.javarush.task.task22.task2201;

public class StringForFirstThreadTooShortException extends RuntimeException {
    public StringForFirstThreadTooShortException(String message) {
        super(message);
    }
    public StringForFirstThreadTooShortException(Throwable e) {
        super(e);
    }
}
