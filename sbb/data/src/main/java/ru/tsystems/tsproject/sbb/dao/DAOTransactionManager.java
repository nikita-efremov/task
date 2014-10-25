package ru.tsystems.tsproject.sbb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

/**
 * Wrapper class for EntityManger. It allows transaction handling in services
 * @author  Nikita Efremov
 * @since   1.0
 */
public class DAOTransactionManager {

    private EntityManager entityManager;

    public DAOTransactionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Method gets transaction from EntityManager and begins it
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
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

    /**
     * Method gets transaction from EntityManager and commits it. After committing, method will check active flag of transaction.
     * It it is true, method will rollback transaction
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
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

    /**
     * Method checks active flag of transaction. It it is true, method will rollback transaction
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
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
