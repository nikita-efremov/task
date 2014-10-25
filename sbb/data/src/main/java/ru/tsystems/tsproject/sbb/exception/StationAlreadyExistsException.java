package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to create station with name,
 * which match with another existing station
 * @author  Nikita Efremov
 * @since   1.0
 */
public class StationAlreadyExistsException extends BusinessLogicException {

    public StationAlreadyExistsException(String message) {
        super(message);
    }

}
