package ru.tsystems.tsproject.sbb.dao.impl;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class StationDAOImpl extends AbstractDAOImpl implements StationDAO {

    private static final Logger log = Logger.getLogger(StationDAOImpl.class);

	public StationDAOImpl(EntityManager em) {
		super(em);
	}

    public void addStation(Station station) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(station);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
        }
    }

    public Station getStationById(int stationID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            return entityManager.find(Station.class, stationID);
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
        }
    }

    public Station getStationByName(String name) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT s FROM Station s where s.name = :name")
                    .setParameter("name", name);
            List stations = query.getResultList();
            if (stations.size() != 0) {
                return  (Station)stations.get(0);
            } else {
                return null;
            }
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public Collection getAllStations() throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT s FROM Station s");
            return query.getResultList();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
        }
    }

    public void updateStation(Station station) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(station);
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
        }
    }

    public void deleteStation(int stationID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            Station station = getStationById(stationID);
            entityManager.remove(entityManager.contains(station) ? station : entityManager.merge(station));
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
        }
    }
}
