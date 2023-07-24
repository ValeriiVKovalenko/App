package com.example.demo.exception;

public class NotFoundException extends RuntimeException {
    public static final String BOTTLE_IS_NOT_EXIST = "Bottle is not exist by id ";
    public NotFoundException(Long bottleId) {
        super(BOTTLE_IS_NOT_EXIST + bottleId);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
