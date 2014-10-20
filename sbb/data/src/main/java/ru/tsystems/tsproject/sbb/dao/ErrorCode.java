package ru.tsystems.tsproject.sbb.dao;

public enum ErrorCode {
    TRANSACTION_NOT_FOUND,
    NON_UNIQUE_FIELD,
    COMMIT_ERROR,
    QUERY_TIMEOUT,
    LOCK_CONFLICT,
    LOCK_TIMEOUT,
    JPA_ERROR,
    UNKNOWN_ERROR,
    ARGUMENT_ERROR,
    STATE_ERROR
}
