package com.recanto.recanto.resources.exceptions;

import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(
            ObjectNotFoundException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Objet Not Found", ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(
            DataIntegrityViolationException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Data Breach", ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

}
