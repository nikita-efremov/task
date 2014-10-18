package ru.tsystems.tsproject.sbb.exception;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 18.10.14
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class PassengerNotRegisteredException extends BusinessLogicException {

    public PassengerNotRegisteredException(String message) {
        super(message);
    }
}