package ru.tsystems.tsproject.sbb.service.impl;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.dao.DAOTransactionManager;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *
 * AdministratorService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AdministratorServiceImpl extends CommonServiceImpl implements AdministratorService {

    private static final Logger log = Logger.getLogger(AdministratorServiceImpl.class);
    private DAOTransactionManager daoTransactionManager;
    private TimetableDAO timetableDAO;

    public AdministratorServiceImpl(DAOTransactionManager daoTransactionManager,StationDAO stationDAO,
                                    TrainDAO trainDAO,
                                    PassengerDAO passengerDAO,
                                    TimetableDAO timetableDAO) {
        super(stationDAO, trainDAO, passengerDAO);
        this.daoTransactionManager = daoTransactionManager;
        this.timetableDAO = timetableDAO;
    }

    public Station addStation(Station station) throws StationAlreadyExistsException, DAOException {
        log.info("Start adding station with name: " + station.getName());
        try {
            daoTransactionManager.beginTransaction();
            if (getStationDAO().getStationByName(station.getName()) == null) {
                getStationDAO().create(station);
            } else {
                throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
            }
            station = getStationDAO().getStationByName(station.getName());
            daoTransactionManager.commitTransaction();
            log.info("Station added: " + station);
        } finally {
            daoTransactionManager.checkActiveTransaction();
        }
        return station;
    }

    public Train addTrain(Train train) throws TrainAlreadyExistsException, DAOException {
        log.info("Start adding train with number: " + train.getNumber() + " and totalSeats: " + train.getSeats());
        try {
            daoTransactionManager.beginTransaction();
            if (getTrainDAO().getTrainByNumber(train.getNumber()) == null) {
                getTrainDAO().create(train);
            } else {
                throw new TrainAlreadyExistsException("Train with number " + train.getNumber() + " already exists");
            }
            train =  getTrainDAO().getTrainByNumber(train.getNumber());
            daoTransactionManager.commitTransaction();
            log.info("Train added: " + train);
        } finally {
            daoTransactionManager.checkActiveTransaction();
        }
        return train;
    }

    public void addTimetable(String trainNumber, String stationName, Date departureDate)
            throws TrainNotExistsException, StationNotExistsException, TrainStopAlreadyExistsException, DAOException {
        log.info("Start adding timetable for train number: " + trainNumber
                + " station name: " +  stationName + " and date: " + departureDate);
        try {
            daoTransactionManager.beginTransaction();
            Train train = findTrain(trainNumber);
            Station station = findStation(stationName);

            Set<Timetable> timetables = train.getTimetables();
            for (Timetable timetable: timetables) {
                if (timetable.getStation().getName().equals(stationName)) {
                    throw new TrainStopAlreadyExistsException("Stop of the train with number " + trainNumber
                            + " already exists on station with name " + stationName);
                }
            }

            Timetable timetable = new Timetable();
            timetable.setTrain(train);
            timetable.setStation(station);
            timetable.setDate(departureDate);
            timetableDAO.create(timetable);
            train.addTimetable(timetable);
            station.addTimetable(timetable);
            daoTransactionManager.commitTransaction();
            log.info("Timetable added: " + timetable);
        } finally {
            daoTransactionManager.checkActiveTransaction();
        }
    }

    public Collection<Passenger> getPassengersByTrain(String trainNumber) throws TrainNotExistsException, DAOException {
        log.info("Start getting passengers by train number: " + trainNumber);
        Train train = findTrain(trainNumber);
        return getPassengerDAO().getPassengersByTrain(train.getId());
    }

    public Collection<Train> getAllTrains() throws DAOException {
        log.info("Start getting all trains");
        return getTrainDAO().<Train>getAll();
    }

    public Collection<Station> getAllStations() throws DAOException {
        log.info("Start getting all stations");
        return getStationDAO().<Station>getAll();
    }
}
