package org.filrouge.gymcommunity.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class AbstractGlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "An unexpected error occurred", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "User Not Found", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handle ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // ErrorDetails class
    public static class ErrorDetails {
        private Date timestamp;
        private String message;
        private int status;
        private String type;

        public ErrorDetails(Date timestamp, String type, String message, int status) {
            this.timestamp = timestamp;
            this.type = type;
            this.message = message;
            this.status = status;
        }

        // Add getters and setters
        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}