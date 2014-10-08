package ru.tsystems.tsproject.sbb.dao;

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
public interface TicketDAO {
    public void addTicket(Ticket ticket) throws DAOException;
    public Ticket getTicketById(int ticketID) throws DAOException;
    public void updateTicket(Ticket ticket) throws DAOException;
    public void deleteTicket(Ticket ticket) throws DAOException;
    public Collection getPassengersByTrain(int trainID) throws DAOException;
}
