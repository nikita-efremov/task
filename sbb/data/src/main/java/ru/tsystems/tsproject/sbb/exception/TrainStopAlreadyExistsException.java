package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to create stop on specified station for train,
 * and stop of this train on specified station already exists
 * which match with another existing train
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TrainStopAlreadyExistsException extends BusinessLogicException {

    public TrainStopAlreadyExistsException(String message) {
        super(message);
    }
}
