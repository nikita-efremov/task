package ru.tsystems.tsproject.sbb.exception;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 05.10.14
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class PassengerAlreadyExistsException extends BusinessLogicException {

    public PassengerAlreadyExistsException(String message) {
        super(message);
    }

}
