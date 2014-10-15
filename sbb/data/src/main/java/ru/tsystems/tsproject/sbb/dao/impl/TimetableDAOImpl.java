package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.api.TimetableDAO;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
public class TimetableDAOImpl extends AbstractDAOImpl implements TimetableDAO {

	public TimetableDAOImpl(EntityManager em) {
		super(em);
	}

    public void addTimetable(Timetable timetable) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(timetable);
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public Timetable getTimetableById(int timetableID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            return entityManager.find(Timetable.class, timetableID);
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void updateTimetable(Timetable timetable) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(timetable);
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void deleteTimetable(int timetableID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            Timetable timetable = getTimetableById(timetableID);
            entityManager.remove(entityManager.contains(timetable) ? timetable : entityManager.merge(timetable));
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
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
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public Collection getTrainsByStationsAndDate(int stationStartID, int stationEndID, Date dateStart, Date dateEnd) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    "select t.train"
                            + " from Timetable t INNER JOIN t.train Train"
                            + " where (t.station.id = :stationStartID or t.station.id = :stationEndID)"
                            + " and (t.date >= :dateStart and t.date <=:dateEnd)"
            )
                    .setParameter("stationStartID", stationStartID)
                    .setParameter("stationEndID", stationEndID)
                    .setParameter("dateStart", dateStart)
                    .setParameter("dateEnd", dateEnd);
            return query.getResultList();
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }
}
