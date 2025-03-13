package com.gneto.financapp.controller.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@ControllerAdvice
public class EntityRestExceptionHandler {

    protected String handleException(Model model, Exception exc, HttpStatus status) {
        EntityErrorResponse error = new EntityErrorResponse();

        error.setStatus(status.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        model.addAttribute("error", error);

        return "error";
    }

    @ExceptionHandler
    public String handleException(Model model, Exception exc) {
        return handleException(model, exc, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public String handleException(Model model, EntityNotFoundException exc) {
        return handleException(model, exc, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public String handleException(Model model, DataIntegrityViolationException exc) {
        return handleException(model, exc, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler
    public String handleException(Model model, DataIntegrityViolationException exc) {
       Exception exception = exc;

        if (exc.getMostSpecificCause() instanceof SQLIntegrityConstraintViolationException) {
            int errorCode = ((SQLIntegrityConstraintViolationException)exc.getMostSpecificCause()).getErrorCode();

            Stream<EntityErrorCode> stream = Arrays.stream(EntityErrorCode.values())
                    .filter(entityErrorCode -> entityErrorCode.getCode() == errorCode);
            Optional<EntityErrorCode> optional = stream.findFirst();

            if (optional.isPresent()) {
                exception = new RuntimeException(optional.get().getMessage(), exc);
            }
            stream.close();
        }

        return handleException(model, exception, HttpStatus.BAD_REQUEST);
    }*/

}
