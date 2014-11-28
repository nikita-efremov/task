package ru.tsystems.tsproject.sbb.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Service
@Transactional
public class PassengerServiceImpl extends CommonServiceImpl implements PassengerService {

    private static final Logger log = Logger.getLogger(PassengerServiceImpl.class);

    @Autowired
    private TicketDAO ticketDAO;

    @Transactional(readOnly = true)
    public Collection<Train> findTrainsByStation(String stationName) throws StationNotExistsException {
        try {
            log.info("Start searching trains by stationName:" + stationName);
            Station station = findStation(stationName);

            Collection<Train> foundTrains = getTrainDAO().getTrainsByStation(station.getId());

            StringBuilder trainString = new StringBuilder();
            for (Train train : foundTrains) {
                trainString.append(",").append(train);
            }
            log.info("Found trains: " + trainString.toString());
            return foundTrains;
        } catch (DAOException e) {
            throw convertDAOException(e);
        }
    }

    @Transactional(readOnly = true)
    public Collection<Train> findTrainsByStationsAndDate(String stationStartName,
                                                         String stationEndName,
                                                         Date start,
                                                         Date end) throws StationNotExistsException {
        try {
            log.info("Start searching trains by stationStartName:" + stationStartName + " stationEndName:" + stationStartName
                    + " start date: " + start + " end date: " + end);
            Station stationStart = findStation(stationStartName);
            Station stationEnd = findStation(stationEndName);
            Collection<Train> directionUnImportantTrains = getTrainDAO().
                    getTrainsByStationsAndDate(stationStart.getId(), stationEnd.getId(), start, end);
            Collection<Train> directionImportantTrains = new LinkedList<Train>();
            Set<String> trains = new HashSet<String>();
            for (Train train : directionUnImportantTrains) {
                if (!trains.contains(train.getNumber())) {
                    Collection<Timetable> trainTimetables = train.getTimetables();
                    long currentTrainStart = 0;
                    long currentTrainEnd = 0;
                    for (Timetable timetable : trainTimetables) {
                        if (timetable.getStation().getId() == stationStart.getId()) {
                            currentTrainStart = timetable.getDate().getTime();
                        }
                        if (timetable.getStation().getId() == stationEnd.getId()) {
                            currentTrainEnd = timetable.getDate().getTime();
                        }
                    }
                    if ((currentTrainStart != 0) && (currentTrainEnd != 0)
                            && (currentTrainStart < currentTrainEnd)
                            && (currentTrainStart >= start.getTime())
                            && (currentTrainEnd <= end.getTime())) {
                        directionImportantTrains.add(train);
                    }
                    trains.add(train.getNumber());
                }
            }
            StringBuilder trainString = new StringBuilder();
            for (Train train : directionImportantTrains) {
                trainString.append(",").append(train);
            }
            log.info("Found trains: " + trainString.toString());
            return directionImportantTrains;
        } catch (DAOException e) {
            throw convertDAOException(e);
        }
    }

    public Ticket purchaseTicket(String trainNumber, String docNumber) throws
            TrainNotExistsException, PassengerNotExistsException, TrainAlreadyFullException,
            PassengerAlreadyRegisteredOnTrainException, TrainAlreadyDepartedException,
            TicketAlreadyExistsException {
        try {
            log.info("Start purchasing ticket for passenger with docNumber: " + docNumber +
                    " for train with number: " + trainNumber);
            Train train = findTrain(trainNumber);
            Passenger passenger = findPassenger(docNumber);
            if (train.getSeats() == 0) {
                throw new TrainAlreadyFullException("Train with number " + train.getNumber() + " does not have free seats");
            }
            Collection<Passenger> trainPassengers = getPassengerDAO().getPassengersByTrain(train.getId());
            for (Passenger trainPassenger : trainPassengers) {
                if ((trainPassenger.getFirstName().equals(passenger.getFirstName()))
                        && (trainPassenger.getLastName().equals(passenger.getLastName()))
                        && (trainPassenger.getBirthDate().getTime() == passenger.getBirthDate().getTime())) {
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
            log.info("Ticket added: " + ticket);
            return ticket;
        } catch (DAOException e) {
            switch (e.getErrorCode()) {
                case NON_UNIQUE_FIELD: {
                    throw new TicketAlreadyExistsException("Ticket for train " + trainNumber + " and passenger "
                            + docNumber + " already purchased");
                }
                default:{
                    throw convertDAOException(e);
                }
            }
        }
    }

    public Passenger addPassenger(Passenger passenger) throws PassengerAlreadyExistsException {
        try {
            log.info("Start adding " + passenger);
            Passenger foundPassenger = getPassengerDAO().getPassengerByDocumentNumber(passenger.getDocNumber());
            if (foundPassenger != null) {
                throw new PassengerAlreadyExistsException("Passenger with document  number " + passenger.getDocNumber()
                        + " already registered in system");
            } else {
                getPassengerDAO().create(passenger);
            }
            passenger = getPassengerDAO().getPassengerByDocumentNumber(passenger.getDocNumber());
            log.info("Passenger added: " + passenger);
            return passenger;
        } catch (DAOException e) {
            switch (e.getErrorCode()) {
                case NON_UNIQUE_FIELD: {
                    throw new PassengerAlreadyExistsException("Passenger with document  number " + passenger.getDocNumber()
                            + " already registered in system");
                }
                default:{
                    throw convertDAOException(e);
                }
            }
        }
    }
}
