package ru.tsystems.tsproject.sbb.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class TicketDAO {

    public void addTicket(Ticket ticket) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    public Collection getPassengersByTrain(int trainID) throws SQLException {
        Session session = null;
        List passengers = new ArrayList<Passenger>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(
                    " select p "
                            + " from Passenger p INNER JOIN p.tickets ticket"
                            + " where Train.id = :trainId "
            )
                    .setInteger("trainId", trainID);
            passengers = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return passengers;
    }
}
