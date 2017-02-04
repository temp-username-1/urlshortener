package me.giannists.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Resource not found")
    @ExceptionHandler(EntityNotFoundException.class)
    public void notFound() {}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Malformed URI")
    @ExceptionHandler(IllegalArgumentException.class)
    public void malformedUri() {}
}