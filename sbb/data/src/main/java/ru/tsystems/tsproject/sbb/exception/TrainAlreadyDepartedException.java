package ru.tsystems.tsproject.sbb.exception;

public class TrainAlreadyDepartedException extends BusinessLogicException {

    public TrainAlreadyDepartedException(String message) {
        super(message);
    }
}
