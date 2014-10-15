package ru.tsystems.tsproject.sbb.exception;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 15.10.14
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class TrainAlreadyDepartedException extends BusinessLogicException {

    public TrainAlreadyDepartedException(String message) {
        super(message);
    }
}
