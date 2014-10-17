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
        for (Train train: directionUnImportantTrains) {
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
        }
        return directionImportantTrains;
    }

    public Collection<Train> getTrainsByStation(Station station) throws DAOException {
        return getTimetableDAO().getTimetableByStation(station.getId());
    }

    public void purchaseTicket(Train train, Passenger passenger)
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
        List<Timetable> timetables = getTimetableDAO().getTimetableByTrain(train.getId());
        if (timetables.size() > 0) {
            Date trainStartTime = timetables.get(0).getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 10);
            Date currentTimeWithStock = calendar.getTime();
            if (!currentTimeWithStock.before(trainStartTime)) {
                throw new TrainAlreadyDepartedException("Train with number " + train.getNumber() + " had been already departed");

            }
        }
        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setPassenger(passenger);
        ticket.setTicketNumber(train.getId() * 1000000000 + passenger.getId());
        getTicketDAO().addTicket(ticket);
        getTrainDAO().decreaseSeatAmount(train.getId());
    }
}
