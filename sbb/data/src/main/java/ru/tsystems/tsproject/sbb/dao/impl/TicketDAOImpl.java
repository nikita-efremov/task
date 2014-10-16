package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.api.TicketDAO;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class TicketDAOImpl extends AbstractDAOImpl implements TicketDAO {

	public TicketDAOImpl(EntityManager em) {
		super(em);
	}

    public void addTicket(Ticket ticket) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                entityManager.persist(ticket);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
            }
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
        }
    }

    public Ticket getTicketById(int ticketID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            return entityManager.find(Ticket.class, ticketID);
		} catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;        
		}
    }

    public void updateTicket(Ticket ticket) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                entityManager.merge(ticket);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
            }
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }

    public void deleteTicket(int ticketID) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                Ticket ticket = getTicketById(ticketID);
                entityManager.remove(entityManager.contains(ticket) ? ticket : entityManager.merge(ticket));
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
            }
        } catch (Exception e) {
			DAOException daoException = new DAOException(e.getMessage());
			daoException.initCause(e.getCause());
			throw daoException;
		}
    }
}
