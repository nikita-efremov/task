package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.ErrorCode;
import ru.tsystems.tsproject.sbb.dao.api.CommonDAO;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;


public abstract class AbstractDAOImpl <T> implements CommonDAO <T> {
	private final EntityManager entityManager;
	
	public AbstractDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

    private Class getTClass() {
        Class currentClass = this.getClass();
        ParameterizedType type = (ParameterizedType)currentClass.getGenericSuperclass();
        return (Class)type.getActualTypeArguments()[0];
    }

    public <T> void create(T t) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                entityManager.persist(t);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
            }
        } catch (IllegalStateException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.STATE_ERROR);
            throw daoException;
        } catch (IllegalArgumentException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.ARGUMENT_ERROR);
            throw daoException;
        } catch (TransactionRequiredException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.TRANSACTION_NOT_FOUND);
            throw daoException;
        } catch (EntityExistsException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.NON_UNIQUE_FIELD);
            throw daoException;
        } catch (RollbackException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.COMMIT_ERROR);
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.JPA_ERROR);
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.UNKNOWN_ERROR);
            throw daoException;
        }
    }

    public <T> T get(int tID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            return (T)entityManager.find(getTClass(), tID);
        } catch (IllegalArgumentException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.ARGUMENT_ERROR);
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.UNKNOWN_ERROR);
            throw daoException;
        }
    }

    public <T> Collection getAll() throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.<T>createQuery(getTClass());
            Root<T> root = criteriaQuery.<T>from(getTClass());
            criteriaQuery.select(root);
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (IllegalStateException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.STATE_ERROR);
            throw daoException;
        } catch (IllegalArgumentException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.ARGUMENT_ERROR);
            throw daoException;
        } catch (TransactionRequiredException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.TRANSACTION_NOT_FOUND);
            throw daoException;
        } catch (QueryTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.QUERY_TIMEOUT);
            throw daoException;
        } catch (PessimisticLockException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.LOCK_CONFLICT);
            throw daoException;
        } catch (LockTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.LOCK_TIMEOUT);
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.JPA_ERROR);
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.UNKNOWN_ERROR);
            throw daoException;
        }
    }

    public <T> void update(T t) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                entityManager.merge(t);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
            }
        } catch (IllegalStateException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.STATE_ERROR);
            throw daoException;
        } catch (IllegalArgumentException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.ARGUMENT_ERROR);
            throw daoException;
        } catch (TransactionRequiredException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.TRANSACTION_NOT_FOUND);
            throw daoException;
        } catch (RollbackException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.COMMIT_ERROR);
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.JPA_ERROR);
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.UNKNOWN_ERROR);
            throw daoException;
        }
    }

    public <T> void delete(int tID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                T t = get(tID);
                entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
            }
        } catch (IllegalStateException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.STATE_ERROR);
            throw daoException;
        } catch (IllegalArgumentException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.ARGUMENT_ERROR);
            throw daoException;
        } catch (RollbackException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.COMMIT_ERROR);
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.JPA_ERROR);
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.UNKNOWN_ERROR);
            throw daoException;
        }
    }
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
