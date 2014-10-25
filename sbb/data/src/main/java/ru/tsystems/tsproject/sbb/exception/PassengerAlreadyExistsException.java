package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to register user with document number,
 * which match with another registered passenger
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerAlreadyExistsException extends BusinessLogicException {

    public PassengerAlreadyExistsException(String message) {
        super(message);
    }

}
