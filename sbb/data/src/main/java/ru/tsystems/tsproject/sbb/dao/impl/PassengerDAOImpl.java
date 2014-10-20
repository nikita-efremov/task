package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.api.PassengerDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.*;
import java.util.Collection;

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
            daoException.setErrorCode("020001");
            throw daoException;
        } catch (TransactionRequiredException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("190001");
            throw daoException;
        } catch (QueryTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("150001");
            throw daoException;
        } catch (PessimisticLockException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("160001");
            throw daoException;
        } catch (LockTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("170001");
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("100001");
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("000001");
            throw daoException;
        }
    }

    public Collection getPassengerByDocumentNumber(String docNumber) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    " select p "
                            + " from Passenger p "
                            + " where p.docNumber = :docNumber"
            )
                    .setParameter("docNumber", docNumber);
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("020001");
            throw daoException;
        } catch (TransactionRequiredException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("190001");
            throw daoException;
        } catch (QueryTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("150001");
            throw daoException;
        } catch (PessimisticLockException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("160001");
            throw daoException;
        } catch (LockTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("170001");
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("100001");
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("000001");
            throw daoException;
        }
    }
}
