package ru.tsystems.tsproject.sbb.exception;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 22.10.14
 * Time: 18:08
 * To change this template use File | Settings | File Templates.
 */
public class PassengerAlreadyRegisteredOnTrainException extends BusinessLogicException {

    public PassengerAlreadyRegisteredOnTrainException(String message) {
        super(message);
    }
}
