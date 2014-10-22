package ru.tsystems.tsproject.sbb.exception;

public class TrainAlreadyFullException extends BusinessLogicException {

    public TrainAlreadyFullException(String message) {
        super(message);
    }

}
