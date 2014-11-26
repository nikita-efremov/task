package ru.tsystems.tsproject.sbb.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tsproject.sbb.dao.ErrorCode;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *
 * AdministratorService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
@Service
@Transactional
public class AdministratorServiceImpl extends CommonServiceImpl implements AdministratorService {

    private static final Logger log = Logger.getLogger(AdministratorServiceImpl.class);

    @Autowired
    private TimetableDAO timetableDAO;

    public Station addStation(Station station) throws StationAlreadyExistsException {
        try {
            log.info("Start adding station with name: " + station.getName());
            if (getStationDAO().getStationByName(station.getName()) == null) {
                getStationDAO().create(station);
            } else {
                throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
            }
            station = getStationDAO().getStationByName(station.getName());
            log.info("Station added: " + station);
            return station;
        } catch (DAOException e) {
            switch (e.getErrorCode()) {
                case NON_UNIQUE_FIELD: {
                    throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
                }
                default:{
                    SystemException systemException = new SystemException(e.getMessage());
                    systemException.setErrorCode(e.getErrorCode());
                    systemException.initCause(e.getCause());
                    throw systemException;
                }
            }
        }
    }

    public Train addTrain(Train train) throws TrainAlreadyExistsException {
        try {
            log.info("Start adding train with number: " + train.getNumber() + " and totalSeats: " + train.getSeats());
            if (getTrainDAO().getTrainByNumber(train.getNumber()) == null) {
                getTrainDAO().create(train);
            } else {
                throw new TrainAlreadyExistsException("Train with number " + train.getNumber() + " already exists");
            }
            train =  getTrainDAO().getTrainByNumber(train.getNumber());
            log.info("Train added: " + train);
            return train;
        } catch (DAOException e) {
            switch (e.getErrorCode()) {
                case NON_UNIQUE_FIELD: {
                    throw new TrainAlreadyExistsException("Train with number " + train.getNumber() + " already exists");
                }
                default:{
                    SystemException systemException = new SystemException(e.getMessage());
                    systemException.setErrorCode(e.getErrorCode());
                    systemException.initCause(e.getCause());
                    throw systemException;
                }
            }
        }
    }

    public void addTimetable(String trainNumber, String stationName, Date departureDate)
            throws TrainNotExistsException, StationNotExistsException, TrainStopAlreadyExistsException {
        try {
            log.info("Start adding timetable for train number: " + trainNumber
                    + " station name: " +  stationName + " and date: " + departureDate);
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
            log.info("Timetable added: " + timetable);
        } catch (DAOException e) {
            switch (e.getErrorCode()) {
                case NON_UNIQUE_FIELD: {
                    throw new TrainStopAlreadyExistsException("Stop of the train with number " + trainNumber
                            + " already exists on station with name " + stationName);
                }
                default:{
                    SystemException systemException = new SystemException(e.getMessage());
                    systemException.setErrorCode(e.getErrorCode());
                    systemException.initCause(e.getCause());
                    throw systemException;
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public Collection<Passenger> getPassengersByTrain(String trainNumber) throws TrainNotExistsException {
        try {
            log.info("Start getting passengers by train number: " + trainNumber);
            Train train = findTrain(trainNumber);
            return getPassengerDAO().getPassengersByTrain(train.getId());
        } catch (DAOException e) {
            SystemException systemException = new SystemException(e.getMessage());
            systemException.setErrorCode(e.getErrorCode());
            systemException.initCause(e.getCause());
            throw systemException;
        }
    }

    @Transactional(readOnly = true)
    public Collection<Train> getAllTrains() {
        try {
            log.info("Start getting all trains");
            return getTrainDAO().<Train>getAll();
        } catch (DAOException e) {
            SystemException systemException = new SystemException(e.getMessage());
            systemException.setErrorCode(e.getErrorCode());
            systemException.initCause(e.getCause());
            throw systemException;
        }
    }

    @Transactional(readOnly = true)
    public Collection<Station> getAllStations() {
        try {
            log.info("Start getting all stations");
            return getStationDAO().<Station>getAll();
        } catch (DAOException e) {
            SystemException systemException = new SystemException(e.getMessage());
            systemException.setErrorCode(e.getErrorCode());
            systemException.initCause(e.getCause());
            throw systemException;
        }
    }

    @Transactional(readOnly = true)
    public Collection<Passenger> getAllPassengers() {
        try {
            log.info("Start getting all passengers");
            return getPassengerDAO().<Station>getAll();
        } catch (DAOException e) {
            SystemException systemException = new SystemException(e.getMessage());
            systemException.setErrorCode(e.getErrorCode());
            systemException.initCause(e.getCause());
            throw systemException;
        }
    }
}
