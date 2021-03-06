package ru.tsystems.tsproject.sbb.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.tsproject.sbb.dao.ErrorCode;
import ru.tsystems.tsproject.sbb.dao.api.TicketDAO;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * TicketDAO interface implementation. Also extends AbstractDAOImpl class with wildcard class = Ticket
 * @author  Nikita Efremov
 * @since   1.0
 */
@Repository
public class TicketDAOImpl extends AbstractDAOImpl<Ticket> implements TicketDAO {

    public Ticket getTicketByNumber(long ticketNumber) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery(
                    " select t "
                            + " from Ticket t "
                            + " where ticketNumber = :ticketNumber"
            )
                    .setParameter("ticketNumber", ticketNumber);
            List tickets = query.getResultList();
            if (tickets.size() != 0) {
                return  (Ticket)tickets.get(0);
            } else {
                return null;
            }
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
