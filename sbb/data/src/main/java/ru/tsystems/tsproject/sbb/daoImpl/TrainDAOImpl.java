package ru.tsystems.tsproject.sbb.daoImpl;


import ru.tsystems.tsproject.sbb.dao.TrainDAO;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.util.JPAUtil;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
			entityManager = JPAUtil.getEntityManger();
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
        EntityManager entityManager = null;
        Train train = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				train = entityManager.find(Train.class, trainID);
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
        return train;
    }

    public void updateTrain(Train train) throws DAOException {
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				entityManager.merge(train);
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

    public void deleteTrain(int trainID) throws DAOException {
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				Train train = getTrainByID(trainID);
				entityManager.remove(entityManager.contains(train) ? train : entityManager.merge(train));
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

    public void decreaseSeatAmount(int trainID) throws DAOException {
        Train train = getTrainByID(trainID);
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				train.setSeats(train.getSeats() - 1);
				entityManager.merge(train);
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

    public Collection getAllTrains() throws DAOException {
        EntityManager entityManager = null;
        List trains = new ArrayList<Train>();
        try { 
			try {
				entityManager = JPAUtil.getEntityManger();
				Query query = entityManager.createQuery("SELECT e FROM Train e");
				trains = query.getResultList();
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
        return trains;
    }
}
