package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;
import java.util.Date;

/**
 * interface for making administrator actions in system
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface AdministratorService extends CommonService {

    /**
     * Adds new station with name, specified in param
     *
     * @param  station
     *         Station instance with default id value and specified name
     *
     * @throws  StationAlreadyExistsException
     *          If station with specified name already exists
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Station addStation(Station station) throws StationAlreadyExistsException, DAOException;

    /**
     * Adds new train with name, specified in param
     *
     * @param train
     *        Train instance with default id, specified number and seats value
     *
     * @throws  TrainAlreadyExistsException
     *          If train with specified name already exists
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Train addTrain(Train train) throws TrainAlreadyExistsException, DAOException;

    /**
     * Adds new timetable with station, train and date specified in param
     *
     * @param trainNumber
     *        Number of train on which timetable must be added
     *
     * @param stationName
     *        Name of station from which train must depart
     *
     * @param departureDate
     *        Date of train departure from station
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     *
     * @throws TrainNotExistsException
     *         if train not found
     *
     * @throws StationNotExistsException
     *         if station not found
     *
     * @throws TrainStopAlreadyExistsException
     *         if stop of train on target station already exists
     */
    public void addTimetable(String trainNumber, String stationName, Date departureDate)
            throws TrainNotExistsException, StationNotExistsException, TrainStopAlreadyExistsException, DAOException;

    /**
     * Gets collection of passengers which have tickets on train, specified in param
     *
     * @param trainNumber
     *        Number of train on which passengers must be found
     *
     * @return collection of passengers
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     *
     * @throws TrainNotExistsException
     *         if train not found
     */
    public Collection<Passenger> getPassengersByTrain(String trainNumber) throws TrainNotExistsException,DAOException;

    /**
     * Gets collection of all exists trains
     *
     * @return collection of trains
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Collection<Station> getAllStations() throws DAOException;

    /**
     * Gets collection of all exists stations
     *
     * @return collection of trains
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Collection<Train> getAllTrains() throws DAOException;

    /**
     * Gets collection of all exists passengers
     *
     * @return collection of passengers
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Collection<Passenger> getAllPassengers() throws DAOException;
}
