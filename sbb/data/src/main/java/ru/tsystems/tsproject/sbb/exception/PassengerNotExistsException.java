package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they searches passenger and expects that it exists. And as a result passenger is not found
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerNotExistsException extends BusinessLogicException {

    public PassengerNotExistsException(String message) {
        super(message);
    }
}