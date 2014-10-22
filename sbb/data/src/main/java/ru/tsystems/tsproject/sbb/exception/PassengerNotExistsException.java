package ru.tsystems.tsproject.sbb.exception;

public class PassengerNotExistsException extends BusinessLogicException {

    public PassengerNotExistsException(String message) {
        super(message);
    }
}