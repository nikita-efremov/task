package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.api.TimetableDAO;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
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
