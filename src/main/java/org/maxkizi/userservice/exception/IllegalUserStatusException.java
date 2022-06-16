package org.maxkizi.userservice.exception;


public class IllegalUserStatusException extends RuntimeException {
    public IllegalUserStatusException() {
        super(Exceptions.ILLEGAL_USER_STATUS_MESSAGE);
    }
}
