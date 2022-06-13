package org.maxkizi.userservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(Exceptions.USER_NOT_FOUND_MESSAGE);
    }
}
