package com.example.mytasksbackend.exception;

import com.example.mytasksbackend.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Violation>> handleBadRequest(MethodArgumentNotValidException ex) {
        List<Violation> violations = ex.getFieldErrors().stream()
                .map(f -> new Violation(f.getField(), f.getDefaultMessage()))
                .collect(Collectors.toList());
        logger.warn("Bad request {}", violations);
        return new ResponseEntity(violations, HttpStatus.BAD_REQUEST);
    }

}
