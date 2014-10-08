package ru.tsystems.tsproject.sbb.service;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;

/**
 *
 * interface for making administrator actions in system
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface AdministratorService {

    /**
     * Adds new station with name, specified in @param station
     *
     * @param  station
     *         Station instance with default id value and specified name
     *
     * @throws  StationAlreadyExistsException
     *          If station with specified name already exists
     */
    public void addStation(Station station) throws StationAlreadyExistsException, DAOException;

    /**
     * Adds new train with name, specified in param
     *
     * @param train
     *        Train instance with specified if, name and timetable
     *
     */
    public void addTrain(Train train) throws DAOException;

    /**
     * Gets collection of passengers which have tickets on train, specified in param
     *
     * @param train
     *        Train instance with specified if, name and timetable
     *
     * @return collection of passengers
     *
     */
    public Collection<Passenger> getPassengersByTrain(Train train) throws DAOException;

    /**
     * Gets collection of all exists trains
     *
     * @return collection of trains
     *
     */
    public Collection<Station> getAllStations() throws DAOException;

    /**
     * Gets collection of all exists stations
     *
     * @return collection of trains
     *
     */
    public Collection<Train> getAllTrains() throws DAOException;
}
