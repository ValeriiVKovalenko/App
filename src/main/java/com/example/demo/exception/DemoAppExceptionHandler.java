package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DemoAppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<String> handleDatabaseOperationException(DatabaseOperationException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }
}
