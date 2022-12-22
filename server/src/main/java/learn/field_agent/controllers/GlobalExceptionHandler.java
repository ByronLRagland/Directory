package learn.field_agent.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        return new ResponseEntity<>(
                new ErrorResponse("Sorry, not sorry. :( "),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(Exception ex){
        return new ResponseEntity<>(
                new ErrorResponse("Data integrity violation"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
