package ru.tsystems.tsproject.sbb.daoImpl;

import ru.tsystems.tsproject.sbb.dao.TicketDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
public class TicketDAOImpl implements TicketDAO {

    public void addTicket(Ticket ticket) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.persist(ticket);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public Collection getPassengersByTrain(int trainID) {
        EntityManager entityManager = null;
        List passengers = new ArrayList<Passenger>();
        try {
            entityManager = JPAUtil.getEntityManger();
            Query query = entityManager.createQuery(
                    " select p "
                            + " from Passenger p INNER JOIN p.tickets ticket"
                            + " where ticket.train.id = :trainId "
            )
                    .setParameter("trainId", trainID);
            passengers = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return passengers;
    }
}
