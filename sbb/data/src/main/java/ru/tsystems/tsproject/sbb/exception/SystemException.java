package ru.tsystems.tsproject.sbb.exception;

import ru.tsystems.tsproject.sbb.dao.ErrorCode;

/**
 * Business methods throws this exception, then they catch DAOException from DAO methods
 * Error code from DAOException must be set to this exception
 * @author  Nikita Efremov
 * @since   2.0
 */
public class SystemException extends RuntimeException {

    private ErrorCode errorCode;

    public SystemException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
