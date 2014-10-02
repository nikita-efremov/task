package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.dao.TicketDAO;
import ru.tsystems.tsproject.sbb.dao.TrainDAO;
import ru.tsystems.tsproject.sbb.daoImpl.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.entity.Train;

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
            PassengerDAOImpl passengerDAOImpl = new PassengerDAOImpl();

            Passenger passenger = new Passenger();
            passenger.setFirstName("John");
            passenger.setLastName("Connor");

            StationDAO stationDAO = new StationDAOImpl();
            Station station = stationDAO.getStationById(4);
            //station.setName("Kabul3");
            //stationDAO.deleteStation(station);
            //stationDAO.updateStation(station);
            //stationDAO.getStationById(4);
            //System.out.println(station.getName());

            TrainDAO trainDAO = new TrainDAOImpl();
            //Train train = new Train();
            //train.setSeats(56);
            //train.setNumber("007x");

            for (Object o: trainDAO.getAllTrains()) {
                Train train = ((Train)o);
                System.out.println(train.getNumber() + "-" + train.getSeats() + "-" + train.getId());
            }

            TicketDAO ticketDAO = new TicketDAOImpl();
            //Ticket ticket = new Ticket();
            //ticket.setPassenger(passengerDAOImpl.getPassengerById(2));
            //ticket.setTrain(trainDAOImpl.getTrainByID(1));
            //ticketDAOImpl.addTicket(ticket);



            //trainDAOImpl.addTrain(train);
            //trainDAOImpl.decreaseSeatAmount(2);
            Collection<Passenger> collection = ticketDAO.getPassengersByTrain(1);

            for (Passenger passenger1: collection) {
                System.out.println(passenger1.getLastName() + " " + passenger1.getFirstName());
            }




            //passengerDAOImpl.updatePassenger(4,passenger);
            //passenger = passengerDAOImpl.getPassengerById(4);
            //System.out.println(passenger.getFirstName());
            //System.out.println(passenger.getLastName());
            //passengerDAOImpl.addPassenger(passenger);
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
