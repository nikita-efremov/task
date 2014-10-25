package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;

/**
 * DAO-class for entity Ticket. Extends CommonDAO interface with wildcard class = Ticket
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface TicketDAO extends CommonDAO <Ticket> {

    /**
     * Get operation: Ticket object will be got by its unique field - ticketNumber
     * @param ticketNumber
     * Tickets`s unique field - number of a ticket
     * @return Ticket
     * Found ticket object
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public Ticket getTicketByNumber(long ticketNumber) throws DAOException;
}
