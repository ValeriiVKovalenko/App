package com.example.demo.exception;

public class DatabaseOperationException extends RuntimeException {

    public DatabaseOperationException() {
        super();
    }

    public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
