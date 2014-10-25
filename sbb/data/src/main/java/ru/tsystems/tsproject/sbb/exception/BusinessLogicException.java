package ru.tsystems.tsproject.sbb.exception;

/**
 * Base class for all business logic exceptions, that occur in system
 * @author  Nikita Efremov
 * @since   1.0
 */
public abstract class BusinessLogicException extends Exception {

    public BusinessLogicException(String message) {
        super(message);
    }
}
