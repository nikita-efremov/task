package ru.tsystems.tsproject.sbb.service.impl;

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
public class AdministratorServiceImpl extends CommonServiceImpl implements AdministratorService {

    private TimetableDAO timetableDAO;

    public AdministratorServiceImpl(StationDAO stationDAO,
                                    TrainDAO trainDAO,
                                    PassengerDAO passengerDAO,
                                    TimetableDAO timetableDAO) {
        super(stationDAO, trainDAO, passengerDAO);
        this.timetableDAO = timetableDAO;
    }

    public Station addStation(Station station) throws StationAlreadyExistsException, DAOException {
        if (getStationDAO().getStationByName(station.getName()) == null) {
            getStationDAO().create(station);
        } else {
            throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
        }
        return getStationDAO().getStationByName(station.getName());
    }

    public Train addTrain(Train train) throws TrainAlreadyExistsException, DAOException {
        if (getTrainDAO().getTrainByNumber(train.getNumber()) == null) {
            getTrainDAO().create(train);
        } else {
            throw new TrainAlreadyExistsException("Train with number " + train.getNumber() + " already exists");
        }
        return getTrainDAO().getTrainByNumber(train.getNumber());
    }

    public void addTimetable(String trainNumber, String stationName, Date departureDate)
            throws TrainNotExistsException, StationNotExistsException, TrainStopAlreadyExistsException, DAOException {
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
    }

    public Collection<Passenger> getPassengersByTrain(String trainNumber) throws TrainNotExistsException, DAOException {
        Train train = findTrain(trainNumber);
        return getPassengerDAO().getPassengersByTrain(train.getId());
    }

    public Collection<Train> getAllTrains() throws DAOException {
        return getTrainDAO().<Train>getAll();
    }

    public Collection<Station> getAllStations() throws DAOException {
        return getStationDAO().<Station>getAll();
    }
}
