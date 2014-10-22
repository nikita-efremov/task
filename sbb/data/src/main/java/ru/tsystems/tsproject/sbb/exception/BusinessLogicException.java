package ru.tsystems.tsproject.sbb.exception;

public abstract class BusinessLogicException extends Exception {

    public BusinessLogicException(String message) {
        super(message);
    }
}
