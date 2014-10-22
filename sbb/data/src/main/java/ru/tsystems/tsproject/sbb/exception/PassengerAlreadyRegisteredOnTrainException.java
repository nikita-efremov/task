package ru.tsystems.tsproject.sbb.exception;

public class PassengerAlreadyRegisteredOnTrainException extends BusinessLogicException {

    public PassengerAlreadyRegisteredOnTrainException(String message) {
        super(message);
    }
}
