package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.ErrorCode;
import ru.tsystems.tsproject.sbb.dao.api.TimetableDAO;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
public class TimetableDAOImpl extends AbstractDAOImpl<Timetable> implements TimetableDAO {

	public TimetableDAOImpl(EntityManager em) {
		super(em);
	}

    public Collection getTimetableByStation(int stationID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    " select t "
                            + " from Timetable t INNER JOIN FETCH t.train Train"
                            + " where t.station.id = :stationID"
            )
                    .setParameter("stationID", stationID);
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

    public List getTimetableByTrain(int trainID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    " select t "
                            + " from Timetable t INNER JOIN FETCH t.train Train"
                            + " where t.train.id = :trainID"
            )
                    .setParameter("trainID", trainID);
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
}
