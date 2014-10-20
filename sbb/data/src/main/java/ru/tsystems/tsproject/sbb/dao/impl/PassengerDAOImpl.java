package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.ErrorCode;
import ru.tsystems.tsproject.sbb.dao.api.PassengerDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class PassengerDAOImpl extends AbstractDAOImpl<Passenger> implements PassengerDAO {

	public PassengerDAOImpl(EntityManager em) {
		super(em);
	}
	
    public Collection getPassengersByTrain(int trainID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    " select p "
                            + " from Passenger p INNER JOIN p.tickets ticket"
                            + " where ticket.train.id = :trainId "
            )
                    .setParameter("trainId", trainID);
            return query.getResultList();
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

    public Passenger getPassengerByDocumentNumber(String docNumber) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    " select p "
                            + " from Passenger p "
                            + " where p.docNumber = :docNumber"
            )
                    .setParameter("docNumber", docNumber);
            List passengers = query.getResultList();
            if (passengers.size() != 0) {
                return (Passenger)passengers.get(0);
            } else {
                return null;
            }
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
}
