package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public interface TicketDAO extends CommonDAO <Ticket> {

    public Collection getTicketByNumber(long ticketNumber) throws DAOException;
}
