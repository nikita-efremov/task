package ru.tsystems.tsproject.sbb;

import org.hibernate.Session;
import ru.tsystems.tsproject.sbb.dao.PassengerDAO;
import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.dao.TicketDAO;
import ru.tsystems.tsproject.sbb.dao.TrainDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.util.HibernateUtil;

import java.util.Collection;

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

            StationDAO stationDAO = new StationDAO();
            Station station = new Station();
            //station = stationDAO.getStationById(1);
            //System.out.println(station.getName());

            TrainDAO trainDAO = new TrainDAO();
            Train train = new Train();
            train.setSeats(56);
            train.setNumber("007x");

            TicketDAO ticketDAO = new TicketDAO();
            Ticket ticket = new Ticket();
            ticket.setPassenger(passengerDAO.getPassengerById(2));
            ticket.setTrain(trainDAO.getTrainByID(1));
            //ticketDAO.addTicket(ticket);



            //trainDAO.addTrain(train);
            //trainDAO.decreaseSeatAmount(2);
            Collection<Passenger> collection = ticketDAO.getPassengersByTrain(1);

            for (Passenger passenger1: collection) {
                System.out.println(passenger1.getLastName() + " " + passenger1.getFirstName());
            }




            //passengerDAO.updatePassenger(4,passenger);
            //passenger = passengerDAO.getPassengerById(4);
            //System.out.println(passenger.getFirstName());
            //System.out.println(passenger.getLastName());
            //passengerDAO.addPassenger(passenger);
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
