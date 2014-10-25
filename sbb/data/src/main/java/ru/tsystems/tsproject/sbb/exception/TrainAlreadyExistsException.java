package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to create train with number,
 * which match with another existing train
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TrainAlreadyExistsException extends BusinessLogicException {

    public TrainAlreadyExistsException(String message) {
        super(message);
    }

}
