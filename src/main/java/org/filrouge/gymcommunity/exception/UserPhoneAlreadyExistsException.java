package org.filrouge.gymcommunity.exception;

public class UserPhoneAlreadyExistsException extends RuntimeException {
    public UserPhoneAlreadyExistsException(String message) {
        super(message);
    }
}
