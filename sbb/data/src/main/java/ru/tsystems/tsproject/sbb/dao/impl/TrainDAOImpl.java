package ru.tsystems.tsproject.sbb.dao.impl;


import ru.tsystems.tsproject.sbb.dao.api.TrainDAO;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class TrainDAOImpl extends AbstractDAOImpl implements TrainDAO {
	
	public TrainDAOImpl(EntityManager em) {
		super(em);
	}

    public void addTrain(Train train) throws DAOException {
        try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(train);
			entityManager.getTransaction().commit();
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public Train getTrainByID(int trainID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            return entityManager.find(Train.class, trainID);
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void updateTrain(Train train) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(train);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void deleteTrain(int trainID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            Train train = getTrainByID(trainID);
            entityManager.remove(entityManager.contains(train) ? train : entityManager.merge(train));
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void decreaseSeatAmount(int trainID) throws DAOException {
        try {
            Train train = getTrainByID(trainID);
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            train.setSeats(train.getSeats() - 1);
            entityManager.merge(train);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            throw daoException;
        }
    }

    public Collection getAllTrains() throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
			Query query = entityManager.createQuery("SELECT e FROM Train e");
			return query.getResultList();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }
}
