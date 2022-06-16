package org.maxkizi.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException() {
        return Exceptions.USER_NOT_FOUND_MESSAGE;
    }

    @ExceptionHandler({IllegalUserStatusException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalUserStatusException() {
        return Exceptions.ILLEGAL_USER_STATUS_MESSAGE;
    }
}
