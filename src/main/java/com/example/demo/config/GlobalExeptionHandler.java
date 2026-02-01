package com.example.demo.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Map;

@RestControllerAdvice
public class GlobalExeptionHandler {

 @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "timestamp", LocalDate.now(),
                "status", 404,
                "error", "Not Foud",
                "message", ex.getMessage()
        ).toString());

 }


    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
            public ResponseEntity<Map<String, Comparable<? extends Comparable<?>>>> handleValidationExeptions(MethodArgumentNotValidException e){
        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.append(fieldError.getDefaultMessage()).append("; ");
        });
        return ResponseEntity.badRequest().body(Map.of(
                "timestamp", LocalDate.now(),
                "status", 400,
                "error", "Bad Request",
                "message",errors.toString()));

    }

}
