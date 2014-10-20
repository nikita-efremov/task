package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.*;
import ru.tsystems.tsproject.sbb.dao.DAOException;
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
public class PassengerServiceImpl implements PassengerService {

    private TrainDAO trainDAO;
    private PassengerDAO passengerDAO;
    private TimetableDAO timetableDAO;
    private TicketDAO ticketDAO;

    public PassengerServiceImpl(TrainDAO trainDAO,
                                PassengerDAO passengerDAO,
                                TimetableDAO timetableDAO,
                                TicketDAO ticketDAO) {
        this.trainDAO = trainDAO;
        this.passengerDAO = passengerDAO;
        this.timetableDAO = timetableDAO;
        this.ticketDAO = ticketDAO;
    }

    public Collection<Train> findTrainsByStationsAndDate(int stationStartID,
                                                         int stationEndID,
                                                         Date start,
                                                         Date end) throws DAOException {
        Collection<Train> directionUnImportantTrains = trainDAO.getTrainsByStationsAndDate(stationStartID, stationEndID, start, end);
        Collection<Train> directionImportantTrains = new LinkedList<Train>();
        Set<String> trains = new HashSet<String>();
        for (Train train: directionUnImportantTrains) {
            if (!trains.contains(train.getNumber())) {
                Collection<Timetable> trainTimetables = timetableDAO.getTimetableByTrain(train.getId());
                Date currentTrainStart = null;
                Date currentTrainEnd = null;
                for (Timetable timetable: trainTimetables) {
                    if (timetable.getStation().getId() == stationStartID) {
                        currentTrainStart = timetable.getDate();
                    }
                    if (timetable.getStation().getId() == stationEndID) {
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

    public Collection<Train> getTrainsByStation(int stationID) throws DAOException {
        return timetableDAO.getTimetableByStation(stationID);
    }

    public Ticket purchaseTicket(Train train, Passenger passenger)
            throws TrainAlreadyFullException, PassengerAlreadyRegisteredException, TrainAlreadyDepartedException, DAOException {
        if (train.getSeats() == 0) {
            throw new TrainAlreadyFullException("Train with number " + train.getNumber() + " does not have free seats");
        }
        Collection<Passenger> trainPassengers = passengerDAO.getPassengersByTrain(train.getId());
        for (Passenger trainPassenger: trainPassengers) {
            if ((trainPassenger.getFirstName().equals(passenger.getFirstName()))
                    && (trainPassenger.getLastName().equals(passenger.getLastName()))
                    && (trainPassenger.getBirthDate().equals(passenger.getBirthDate()))) {
                throw new PassengerAlreadyRegisteredException("Passenger " + passenger.getFirstName() + " " + passenger.getLastName()
                        + " had been already registered on train " + train.getNumber());
            }
        }
        TreeSet<Timetable> timetables = new TreeSet<Timetable>(timetableDAO.getTimetableByTrain(train.getId()));
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
        trainDAO.decreaseSeatAmount(train.getId());
        ticketDAO.create(ticket);

        List<Ticket> tickets = new LinkedList<Ticket>(ticketDAO.getTicketByNumber(ticketNumber));

        if (tickets.size() > 0) {
            ticket = tickets.get(0);
        }
        return ticket;
    }

    public void addPassenger(Passenger passenger) throws PassengerAlreadyRegisteredException, DAOException {
        Passenger foundPassenger = passengerDAO.getPassengerByDocumentNumber(passenger.getDocNumber());
        if (foundPassenger != null) {
            throw new PassengerAlreadyRegisteredException("Passenger with document  number " + passenger.getDocNumber()
                    + " already registered in system");
        } else {
            passengerDAO.create(passenger);
        }
    }
}
