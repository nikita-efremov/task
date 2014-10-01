package ru.tsystems.tsproject.sbb;

import org.hibernate.Session;
import ru.tsystems.tsproject.sbb.dao.PassengerDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            PassengerDAO passengerDAO = new PassengerDAO();

            Passenger passenger = new Passenger();
            passenger.setFirstName("John");
            passenger.setLastName("Connor");


            //passengerDAO.updatePassenger(4,passenger);
            //passenger = passengerDAO.getPassengerById(4);
            //System.out.println(passenger.getFirstName());
            //System.out.println(passenger.getLastName());
            passengerDAO.addPassenger(passenger);
        } catch (Exception e) {
            System.out.println(e);
        }

        /*
        System.out.println("Hibernate one to many (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(passenger);

        session.getTransaction().commit();

        session.beginTransaction();

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrainID(2);

        passenger.getTickets().add(ticket);
        session.save(ticket);

        session.getTransaction().commit();
        System.out.println("Done");
        */

    }
}
