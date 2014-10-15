package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.api.PassengerDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

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
		try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(passenger);
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void updatePassenger(Passenger passenger) throws DAOException {
		try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(passenger);
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}		
    }

    public Passenger getPassengerById(int passengerID) throws DAOException {
		try {
            EntityManager entityManager = getEntityManager();
            return entityManager.find(Passenger.class, passengerID);
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
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
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void deletePassenger(int passengerID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            Passenger passenger = getPassengerById(passengerID);
            entityManager.remove(entityManager.contains(passenger) ? passenger : entityManager.merge(passenger));
            entityManager.getTransaction().commit();
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;        
		} 
    }
}
