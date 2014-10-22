package ru.tsystems.tsproject.sbb.dao.impl;

import ru.tsystems.tsproject.sbb.dao.ErrorCode;
import ru.tsystems.tsproject.sbb.dao.api.TicketDAO;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import javax.persistence.*;
import java.util.Collection;

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
            daoException.setErrorCode(ErrorCode.ARGUMENT_ERROR);
            throw daoException;
        } catch (TransactionRequiredException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.TRANSACTION_NOT_FOUND);
            throw daoException;
        } catch (QueryTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.QUERY_TIMEOUT);
            throw daoException;
        } catch (PessimisticLockException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.LOCK_CONFLICT);
            throw daoException;
        } catch (LockTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.LOCK_TIMEOUT);
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.JPA_ERROR);
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode(ErrorCode.UNKNOWN_ERROR);
            throw daoException;
        }
    }
}
