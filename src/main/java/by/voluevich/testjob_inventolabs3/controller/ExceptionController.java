package by.voluevich.testjob_inventolabs3.controller;


import by.voluevich.testjob_inventolabs3.exception.InitDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InitDataException.class)
    protected void handleAccessDeniedException(InitDataException ex) {
        System.out.println(String.format("File (%s) not loading. Exception: %s. Description: %s.",
                ex.getFile(),
                ex.getException(),
                ex.getDesc()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<String> handleEntityNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check input data.");
    }
}
