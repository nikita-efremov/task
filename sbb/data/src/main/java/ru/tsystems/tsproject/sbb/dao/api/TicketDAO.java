package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;

public interface TicketDAO extends CommonDAO <Ticket> {

    public Collection getTicketByNumber(long ticketNumber) throws DAOException;
}
