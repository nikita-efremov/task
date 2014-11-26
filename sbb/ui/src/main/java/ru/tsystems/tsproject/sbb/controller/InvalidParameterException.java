package ru.tsystems.tsproject.sbb.controller;

/**
 * It is thrown, when controller gets invalid request parameter
 * @author  Nikita Efremov
 * @since   2.0
 */
public class InvalidParameterException extends Exception {

    public InvalidParameterException(String message) {
        super(message);
    }
}
