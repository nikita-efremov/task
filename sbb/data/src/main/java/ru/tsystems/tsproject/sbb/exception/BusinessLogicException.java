package ru.tsystems.tsproject.sbb.exception;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 05.10.14
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class BusinessLogicException extends Exception {

    public BusinessLogicException(String message) {
        super(message);
    }
}
