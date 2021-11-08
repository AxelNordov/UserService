package com.axel.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(
                "message", "Something went wrong",
                "originalMessage", e.getMessage())
        );
    }

    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<Object> handleSQLException(SQLException e) {
        return ResponseEntity.badRequest().body(Map.of(
                "message", "Something went wrong3333333",
                "originalMessage", e.getMessage())
        );
    }
}
