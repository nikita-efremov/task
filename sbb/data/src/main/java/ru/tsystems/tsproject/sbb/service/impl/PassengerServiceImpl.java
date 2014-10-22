package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.*;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

import java.util.*;

/**
 *
 * PassengerService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerServiceImpl extends CommonServiceImpl implements PassengerService {

    private TimetableDAO timetableDAO;
    private TicketDAO ticketDAO;

    public PassengerServiceImpl(StationDAO stationDAO,
                                TrainDAO trainDAO,
                                PassengerDAO passengerDAO,
                                TimetableDAO timetableDAO,
                                TicketDAO ticketDAO) {
        super(stationDAO, trainDAO, passengerDAO);
        this.timetableDAO = timetableDAO;
        this.ticketDAO = ticketDAO;
    }

    public Collection<Train> findTrainsByStationsAndDate(String stationStartName,
                                                         String stationEndName,
                                                         Date start,
                                                         Date end) throws StationNotExistsException, DAOException {
        Station stationStart = findStation(stationStartName);
        Station stationEnd = findStation(stationEndName);
        Collection<Train> directionUnImportantTrains = getTrainDAO().
                getTrainsByStationsAndDate(stationStart.getId(), stationEnd.getId(), start, end);
        Collection<Train> directionImportantTrains = new LinkedList<Train>();
        Set<String> trains = new HashSet<String>();
        for (Train train: directionUnImportantTrains) {
            if (!trains.contains(train.getNumber())) {
                Collection<Timetable> trainTimetables = train.getTimetables();
                Date currentTrainStart = null;
                Date currentTrainEnd = null;
                for (Timetable timetable: trainTimetables) {
                    if (timetable.getStation().getId() == stationStart.getId()) {
                        currentTrainStart = timetable.getDate();
                    }
                    if (timetable.getStation().getId() == stationEnd.getId()) {
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

    public Ticket purchaseTicket(String trainNumber, String docNumber) throws
            TrainNotExistsException, PassengerNotExistsException, TrainAlreadyFullException,
            PassengerAlreadyRegisteredOnTrainException, TrainAlreadyDepartedException, DAOException {
        Train train = findTrain(trainNumber);
        Passenger passenger = findPassenger(docNumber);
        if (train.getSeats() == 0) {
            throw new TrainAlreadyFullException("Train with number " + train.getNumber() + " does not have free seats");
        }
        Collection<Passenger> trainPassengers = getPassengerDAO().getPassengersByTrain(train.getId());
        for (Passenger trainPassenger: trainPassengers) {
            if ((trainPassenger.getFirstName().equals(passenger.getFirstName()))
                    && (trainPassenger.getLastName().equals(passenger.getLastName()))
                    && (trainPassenger.getBirthDate().equals(passenger.getBirthDate()))) {
                throw new PassengerAlreadyRegisteredOnTrainException("Passenger " + passenger.getFirstName() + " " + passenger.getLastName()
                        + " had been already registered on train " + train.getNumber());
            }
        }
        TreeSet<Timetable> timetables = new TreeSet<Timetable>(train.getTimetables());
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
        ticketDAO.create(ticket);

        List<Ticket> tickets = new LinkedList<Ticket>(ticketDAO.getTicketByNumber(ticketNumber));

        if (tickets.size() > 0) {
            ticket = tickets.get(0);
        }
        return ticket;
    }

    public Passenger addPassenger(Passenger passenger) throws PassengerAlreadyExistsException, DAOException {
        Passenger foundPassenger = getPassengerDAO().getPassengerByDocumentNumber(passenger.getDocNumber());
        if (foundPassenger != null) {
            throw new PassengerAlreadyExistsException("Passenger with document  number " + passenger.getDocNumber()
                    + " already registered in system");
        } else {
            getPassengerDAO().create(passenger);
        }
        return getPassengerDAO().getPassengerByDocumentNumber(passenger.getDocNumber());
    }
}
