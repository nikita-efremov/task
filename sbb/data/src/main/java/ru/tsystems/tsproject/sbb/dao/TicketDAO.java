package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Ticket;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public interface TicketDAO {
    public void addTicket(Ticket ticket);
    public Collection getPassengersByTrain(int trainID);
}
