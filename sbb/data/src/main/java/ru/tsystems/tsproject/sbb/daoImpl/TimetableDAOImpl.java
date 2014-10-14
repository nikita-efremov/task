package ru.tsystems.tsproject.sbb.daoImpl;

import ru.tsystems.tsproject.sbb.dao.TimetableDAO;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.util.JPAUtil;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
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
public class TimetableDAOImpl extends AbstractDAOImpl implements TimetableDAO {

	public TimetableDAOImpl(EntityManager em) {
		super(em);
	}

    public void addTimetable(Timetable timetable) throws DAOException {
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				entityManager.persist(timetable);
				entityManager.getTransaction().commit();
			} finally {
				if ((entityManager != null) && (entityManager.isOpen())) {
					entityManager.close();
				}
			}
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public Timetable getTimetableById(int timetableID) throws DAOException {
        EntityManager entityManager = null;
        Timetable Timetable = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				Timetable = entityManager.find(Timetable.class, timetableID);
			} finally {
				if ((entityManager != null) && (entityManager.isOpen())) {
					entityManager.close();
				}
			}
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
        return Timetable;
    }

    public void updateTimetable(Timetable timetable) throws DAOException {
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				entityManager.merge(timetable);
				entityManager.getTransaction().commit();
			} finally {
				if ((entityManager != null) && (entityManager.isOpen())) {
					entityManager.close();
				}
			}
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void deleteTimetable(int timetableID) throws DAOException {
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				Timetable timetable = getTimetableById(timetableID);
				entityManager.remove(entityManager.contains(timetable) ? timetable : entityManager.merge(timetable));
				entityManager.getTransaction().commit();
			} finally {
				if ((entityManager != null) && (entityManager.isOpen())) {
					entityManager.close();
				}
			}
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public Collection getTimetableByStation(int stationID) throws DAOException {
        EntityManager entityManager = null;
        List timetableList = new ArrayList<Timetable>();
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				Query query = entityManager.createQuery(
						" select t "
								+ " from Timetable t INNER JOIN FETCH t.train Train"
								+ " where t.station.id = :stationID"
				)
						.setParameter("stationID", stationID);
				timetableList = query.getResultList();
			} finally {
				if ((entityManager != null) && (entityManager.isOpen())) {
					entityManager.close();
				}
			}	
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
        return timetableList;
    }

    public Collection getTrainsByStationsAndDate(int stationStartID, int stationEndID, Date dateStart, Date dateEnd) throws DAOException {
        EntityManager entityManager = null;
        List trainList = new ArrayList<Train>();
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				Query query = entityManager.createQuery(
						"select t.train"
								+ " from Timetable t INNER JOIN t.train Train"
								+ " where (t.station.id = :stationStartID or t.station.id = :stationEndID) and (t.date >= :dateStart and t.date <=:dateEnd)"
				)
						.setParameter("stationStartID", stationStartID)
						.setParameter("stationEndID", stationEndID)
						.setParameter("dateStart", dateStart)
						.setParameter("dateEnd", dateEnd);

				trainList = query.getResultList();
			} finally {
				if ((entityManager != null) && (entityManager.isOpen())) {
					entityManager.close();
				}
			}
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
        return trainList;
    }
}
