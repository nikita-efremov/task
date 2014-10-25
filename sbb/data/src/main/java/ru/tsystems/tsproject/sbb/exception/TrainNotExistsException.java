package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they searches train and expects that it exists. And as a result train is not found
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TrainNotExistsException extends BusinessLogicException {

    public TrainNotExistsException(String message) {
        super(message);
    }

}
