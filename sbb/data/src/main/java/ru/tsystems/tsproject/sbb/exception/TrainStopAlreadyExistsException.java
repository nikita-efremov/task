package ru.tsystems.tsproject.sbb.exception;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 17.10.14
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
public class TrainStopAlreadyExistsException extends BusinessLogicException {

    public TrainStopAlreadyExistsException(String message) {
        super(message);
    }
}
