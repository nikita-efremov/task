package ru.tsystems.tsproject.sbb.dao;

/**
 * Exception which will contain all exceptions, which will appear in DAO-classes
 * @author  Nikita Efremov
 * @since   1.0
 */
public class DAOException extends Exception {

    /**
     * Enum with code of occurred error
     */
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
