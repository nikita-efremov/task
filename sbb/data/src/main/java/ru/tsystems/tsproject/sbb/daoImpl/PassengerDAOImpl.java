package ru.tsystems.tsproject.sbb.daoImpl;

import ru.tsystems.tsproject.sbb.dao.PassengerDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.util.JPAUtil;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class PassengerDAOImpl implements PassengerDAO {

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

    public void deletePassenger(Passenger passenger) throws DAOException {
        EntityManager entityManager = null;
        try {
			try {
				entityManager = JPAUtil.getEntityManger();
				entityManager.getTransaction().begin();
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
