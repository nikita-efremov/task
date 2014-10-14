package ru.tsystems.tsproject.sbb.daoImpl;

import ru.tsystems.tsproject.sbb.dao.PassengerDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
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
 * Date: 01.10.14
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class PassengerDAOImpl extends AbstractDAOImpl implements PassengerDAO {

	public PassengerDAOImpl(EntityManager em) {
		super(em);
	}

    public void addPassenger(Passenger passenger) throws DAOException {
        EntityManager entityManager = null;
		try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				entityManager.persist(passenger);
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

    public void updatePassenger(Passenger passenger) throws DAOException {
        EntityManager entityManager = null;
		try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				entityManager.merge(passenger);
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

    public Passenger getPassengerById(int passengerID) throws DAOException {
        EntityManager entityManager = null;
        Passenger passenger = null;
		try {
			try {
				entityManager = JPAUtil.getEntityManger();
				passenger = entityManager.find(Passenger.class, passengerID);
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
        return passenger;
    }
	
    public Collection getPassengersByTrain(int trainID) throws DAOException {
        EntityManager entityManager = null;
        List passengers = new ArrayList<Passenger>();
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				Query query = entityManager.createQuery(
						" select p "
								+ " from Passenger p INNER JOIN p.tickets ticket"
								+ " where ticket.train.id = :trainId "
				)
						.setParameter("trainId", trainID);
				passengers = query.getResultList();
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
        return passengers;
    }

    public void deletePassenger(int passengerID) throws DAOException {
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
				Passenger passenger = getPassengerById(passengerID);
				entityManager.remove(entityManager.contains(passenger) ? passenger : entityManager.merge(passenger));
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
}
