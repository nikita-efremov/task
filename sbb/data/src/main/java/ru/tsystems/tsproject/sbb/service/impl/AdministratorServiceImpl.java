package ru.tsystems.tsproject.sbb.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class AdministratorServiceImpl extends CommonServiceImpl implements AdministratorService {

    private static final Logger log = Logger.getLogger(AdministratorServiceImpl.class);

    @Autowired
    private TimetableDAO timetableDAO;

    @Transactional
    public Station addStation(Station station) throws StationAlreadyExistsException, DAOException {
        log.info("Start adding station with name: " + station.getName());
        if (getStationDAO().getStationByName(station.getName()) == null) {
            getStationDAO().create(station);
        } else {
            throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
        }
        station = getStationDAO().getStationByName(station.getName());
        log.info("Station added: " + station);
        return station;
    }

    @Transactional
    public Train addTrain(Train train) throws TrainAlreadyExistsException, DAOException {
        log.info("Start adding train with number: " + train.getNumber() + " and totalSeats: " + train.getSeats());
        if (getTrainDAO().getTrainByNumber(train.getNumber()) == null) {
            getTrainDAO().create(train);
        } else {
            throw new TrainAlreadyExistsException("Train with number " + train.getNumber() + " already exists");
        }
        train =  getTrainDAO().getTrainByNumber(train.getNumber());
        log.info("Train added: " + train);
        return train;
    }

    @Transactional
    public void addTimetable(String trainNumber, String stationName, Date departureDate)
            throws TrainNotExistsException, StationNotExistsException, TrainStopAlreadyExistsException, DAOException {
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
    }

    @Transactional
    public Collection<Passenger> getPassengersByTrain(String trainNumber) throws TrainNotExistsException, DAOException {
        log.info("Start getting passengers by train number: " + trainNumber);
        Train train = findTrain(trainNumber);
        return getPassengerDAO().getPassengersByTrain(train.getId());
    }

    @Transactional
    public Collection<Train> getAllTrains() throws DAOException {
        log.info("Start getting all trains");
        return getTrainDAO().<Train>getAll();
    }

    @Transactional
    public Collection<Station> getAllStations() throws DAOException {
        log.info("Start getting all stations");
        return getStationDAO().<Station>getAll();
    }
}
