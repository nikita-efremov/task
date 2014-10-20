package ru.tsystems.tsproject.sbb.dao;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 05.10.14
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class DAOException extends Exception {

    private ErrorCode errorCode;

    public DAOException(String message) {
        super(message);
        errorCode = ErrorCode.UNKNOWN_ERROR;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
