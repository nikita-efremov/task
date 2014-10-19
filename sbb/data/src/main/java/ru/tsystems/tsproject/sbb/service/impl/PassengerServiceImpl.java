package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.*;
import ru.tsystems.tsproject.sbb.exception.DAOException;
import ru.tsystems.tsproject.sbb.exception.PassengerAlreadyRegisteredException;
import ru.tsystems.tsproject.sbb.exception.TrainAlreadyDepartedException;
import ru.tsystems.tsproject.sbb.exception.TrainAlreadyFullException;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

import java.util.*;

/**
 *
 * PassengerService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerServiceImpl extends AbstractServiceImpl implements PassengerService {

    public PassengerServiceImpl(StationDAO stationDAO,
                                TrainDAO trainDAO,
                                PassengerDAO passengerDAO,
                                TimetableDAO timetableDAO,
                                TicketDAO ticketDAO) {
        super(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
    }

    public Collection<Train> findTrainsByStationsAndDate(Station stationStart,
                                                         Station stationEnd,
                                                         Date start,
                                                         Date end) throws DAOException {
        Collection<Train> directionUnImportantTrains = getTrainDAO().getTrainsByStationsAndDate(stationStart.getId(), stationEnd.getId(), start, end);
        Collection<Train> directionImportantTrains = new LinkedList<Train>();
        Set<String> trains = new HashSet<String>();
        for (Train train: directionUnImportantTrains) {
            if (!trains.contains(train.getNumber())) {
                Collection<Timetable> trainTimetables = getTimetableDAO().getTimetableByTrain(train.getId());
                Date currentTrainStart = null;
                Date currentTrainEnd = null;
                for (Timetable timetable: trainTimetables) {
                    if (timetable.getStation().equals(stationStart)) {
                        currentTrainStart = timetable.getDate();
                    }
                    if (timetable.getStation().equals(stationEnd)) {
                        currentTrainEnd = timetable.getDate();
                    }
                }
                if ((currentTrainStart != null) && (currentTrainEnd != null) && (currentTrainStart.before(currentTrainEnd))) {
                    directionImportantTrains.add(train);
                }
                trains.add(train.getNumber());
            }
        }
        return directionImportantTrains;
    }

    public Collection<Train> getTrainsByStation(Station station) throws DAOException {
        return getTimetableDAO().getTimetableByStation(station.getId());
    }

    public Ticket purchaseTicket(Train train, Passenger passenger)
            throws TrainAlreadyFullException, PassengerAlreadyRegisteredException, TrainAlreadyDepartedException, DAOException {
        if (train.getSeats() == 0) {
            throw new TrainAlreadyFullException("Train with number " + train.getNumber() + " does not have free seats");
        }
        Collection<Passenger> trainPassengers = getPassengerDAO().getPassengersByTrain(train.getId());
        for (Passenger trainPassenger: trainPassengers) {
            if ((trainPassenger.getFirstName().equals(passenger.getFirstName()))
                    && (trainPassenger.getLastName().equals(passenger.getLastName()))
                    && (trainPassenger.getBirthDate().equals(passenger.getBirthDate()))) {
                throw new PassengerAlreadyRegisteredException("Passenger " + passenger.getFirstName() + " " + passenger.getLastName()
                        + " had been already registered on train " + train.getNumber());
            }
        }
        TreeSet<Timetable> timetables = new TreeSet<Timetable>(getTimetableDAO().getTimetableByTrain(train.getId()));
        if (timetables.size() > 0) {
            Date trainStartTime = timetables.first().getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 10);
            Date currentTimeWithStock = calendar.getTime();
            if (!currentTimeWithStock.before(trainStartTime)) {
                throw new TrainAlreadyDepartedException("Train with number " + train.getNumber() + " had been already departed");

            }
        }
        long ticketNumber = train.getId() * 1000000 + passenger.getId();
        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setPassenger(passenger);
        ticket.setTicketNumber(ticketNumber);
        getTrainDAO().decreaseSeatAmount(train.getId());
        getTicketDAO().addTicket(ticket);

        List<Ticket> tickets = new LinkedList<Ticket>(getTicketDAO().getTicketByNumber(ticketNumber));

        if (tickets.size() > 0) {
            ticket = tickets.get(0);
        }
        return ticket;
    }

    public void addPassenger(Passenger passenger) throws PassengerAlreadyRegisteredException, DAOException {
        Collection<Passenger> passengers = getPassengerDAO().getPassengerByDocumentNumber(passenger.getDocNumber());
        if ((passengers != null) && (passengers.size() > 0)) {
            throw new PassengerAlreadyRegisteredException("Passenger with document  number " + passenger.getDocNumber()
                    + " already registered in system");
        } else {
            getPassengerDAO().addPassenger(passenger);
        }
    }
}
