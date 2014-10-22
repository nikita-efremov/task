package ru.tsystems.tsproject.sbb.dao;

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
