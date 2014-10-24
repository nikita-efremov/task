package ru.tsystems.tsproject.sbb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

public class DAOTransactionManager {

    private EntityManager entityManager;

    public DAOTransactionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void beginTransaction() throws DAOException {
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.STATE_ERROR);
            throw daoException;
        }
    }

    public void commitTransaction() throws DAOException {
        try {
            try {
                entityManager.getTransaction().commit();
            }
            finally {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            }
        } catch (RollbackException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.COMMIT_ERROR);
            throw daoException;
        }
    }

    public void checkActiveTransaction() throws DAOException {
        try {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } catch (IllegalStateException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.STATE_ERROR);
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.JPA_ERROR);
            throw daoException;
        }
    }
}
