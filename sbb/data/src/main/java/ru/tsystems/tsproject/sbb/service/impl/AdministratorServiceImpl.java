package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.exception.TrainAlreadyExistsException;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;

/**
 *
 * AdministratorService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AdministratorServiceImpl implements AdministratorService {

    private StationDAO stationDAO;
    private TrainDAO trainDAO;
    private PassengerDAO passengerDAO;
    private TimetableDAO timetableDAO;

    public AdministratorServiceImpl(StationDAO stationDAO,
                                    TrainDAO trainDAO,
                                    PassengerDAO passengerDAO,
                                    TimetableDAO timetableDAO) {
        this.stationDAO = stationDAO;
        this.trainDAO = trainDAO;
        this.passengerDAO = passengerDAO;
        this.timetableDAO = timetableDAO;
    }

    public void addStation(Station station) throws StationAlreadyExistsException, DAOException {
        if (stationDAO.getStationByName(station.getName()) == null) {
            stationDAO.create(station);
        } else {
            throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
        }
    }

    public void addTrain(Train train) throws TrainAlreadyExistsException, DAOException {
        if (trainDAO.getTrainByNumber(train.getNumber()) == null) {
            trainDAO.create(train);
        } else {
            throw new TrainAlreadyExistsException("Train with number " + train.getNumber() + " already exists");
        }
    }

    public void addTimetable(Timetable timetable) throws DAOException {
        timetableDAO.create(timetable);
    }

    public Collection<Passenger> getPassengersByTrain(int trainID) throws DAOException {
        return passengerDAO.getPassengersByTrain(trainID);
    }

    public Collection<Train> getAllTrains() throws DAOException {
        return trainDAO.<Train>getAll();
    }

    public Collection<Station> getAllStations() throws DAOException {
        return stationDAO.<Station>getAll();
    }
}
