package org.ppke.itk.pcbm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        String message = ex.getMessage() != null ? ex.getMessage() : "The requested resource could not be found.";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = ex.getMessage() != null ? ex.getMessage() : "Invalid input provided. Please check your request.";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
