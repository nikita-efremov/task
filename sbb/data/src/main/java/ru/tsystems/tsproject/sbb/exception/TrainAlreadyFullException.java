package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to purchase ticket on train,
 * which has not got free seats
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TrainAlreadyFullException extends BusinessLogicException {

    public TrainAlreadyFullException(String message) {
        super(message);
    }

}
