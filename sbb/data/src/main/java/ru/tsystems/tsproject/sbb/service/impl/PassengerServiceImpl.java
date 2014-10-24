package ru.tsystems.tsproject.sbb.service.impl;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.dao.DAOTransactionManager;
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

    private static final Logger log = Logger.getLogger(PassengerServiceImpl.class);
    private DAOTransactionManager daoTransactionManager;
    private TicketDAO ticketDAO;

    public PassengerServiceImpl(DAOTransactionManager daoTransactionManager,
                                StationDAO stationDAO,
                                TrainDAO trainDAO,
                                PassengerDAO passengerDAO,
                                TicketDAO ticketDAO) {
        super(stationDAO, trainDAO, passengerDAO);
        this.daoTransactionManager = daoTransactionManager;
        this.ticketDAO = ticketDAO;
    }

    public Collection<Train> findTrainsByStationsAndDate(String stationStartName,
                                                         String stationEndName,
                                                         Date start,
                                                         Date end) throws StationNotExistsException, DAOException {
        log.info("Start searching trains by stationStartName:" + stationStartName + " stationEndName:" + stationStartName
                + " start date: " + start + " end date: " + end);
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
        StringBuilder trainString = new StringBuilder();
        for (Train train: directionImportantTrains) {
            trainString.append(",").append(train);
        }
        log.info("Found trains: " + trainString.toString());
        return directionImportantTrains;
    }

    public Ticket purchaseTicket(String trainNumber, String docNumber) throws
            TrainNotExistsException, PassengerNotExistsException, TrainAlreadyFullException,
            PassengerAlreadyRegisteredOnTrainException, TrainAlreadyDepartedException, DAOException {
        log.info("Start purchasing ticket for passenger with docNumber: " + docNumber +
                " for train with number: " + trainNumber);
        try {
            daoTransactionManager.beginTransaction();
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

            ticket = ticketDAO.getTicketByNumber(ticketNumber);
            daoTransactionManager.commitTransaction();
            log.info("Ticket added: " + ticket);
            return ticket;
        } finally {
            daoTransactionManager.checkActiveTransaction();
        }
    }

    public Passenger addPassenger(Passenger passenger) throws PassengerAlreadyExistsException, DAOException {
        log.info("Start adding " + passenger);
        try {
            daoTransactionManager.beginTransaction();
            Passenger foundPassenger = getPassengerDAO().getPassengerByDocumentNumber(passenger.getDocNumber());
            if (foundPassenger != null) {
                throw new PassengerAlreadyExistsException("Passenger with document  number " + passenger.getDocNumber()
                        + " already registered in system");
            } else {
                getPassengerDAO().create(passenger);
            }
            passenger = getPassengerDAO().getPassengerByDocumentNumber(passenger.getDocNumber());
            daoTransactionManager.commitTransaction();
            log.info("Passenger added: " + passenger);
        } finally {
            daoTransactionManager.checkActiveTransaction();
        }
        return passenger;
    }
}
