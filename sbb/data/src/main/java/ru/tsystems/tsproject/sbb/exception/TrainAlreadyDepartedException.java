package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to purchase ticket on train,
 * which departure time is not smaller for 10 minutes than current time
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TrainAlreadyDepartedException extends BusinessLogicException {

    public TrainAlreadyDepartedException(String message) {
        super(message);
    }
}
