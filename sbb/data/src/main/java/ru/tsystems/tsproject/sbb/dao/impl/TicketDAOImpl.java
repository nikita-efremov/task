package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.api.TicketDAO;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class TicketDAOImpl extends AbstractDAOImpl<Ticket> implements TicketDAO {

	public TicketDAOImpl(EntityManager em) {
		super(em);
	}

    public Collection getTicketByNumber(long ticketNumber) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    " select t "
                            + " from Ticket t "
                            + " where ticketNumber = :ticketNumber"
            )
                    .setParameter("ticketNumber", ticketNumber);
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
