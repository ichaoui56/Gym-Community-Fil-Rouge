package org.filrouge.gymcommunity.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.filrouge.gymcommunity.dto.ErrorDTO;
import org.filrouge.gymcommunity.response.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception e) {
        return Response.error(400 , e.getMessage() , null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(MethodArgumentNotValidException e) {
        Map<String , String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(),  fieldError.getDefaultMessage());
        });

        return Response.error(400 , "errors"  , errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        return Response.error(404, ex.getMessage() , null);
    }
}
