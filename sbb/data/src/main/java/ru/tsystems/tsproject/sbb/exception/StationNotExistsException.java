package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they searches station and expects that it exists. And as a result station is not found
 * @author  Nikita Efremov
 * @since   1.0
 */
public class StationNotExistsException extends BusinessLogicException {

    public StationNotExistsException(String message) {
        super(message);
    }

}
