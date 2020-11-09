package com.spring.boot.task.exceptions;

public class NoHeaderException extends RuntimeException {
    public NoHeaderException(String message) {
        super(message);
    }
}
