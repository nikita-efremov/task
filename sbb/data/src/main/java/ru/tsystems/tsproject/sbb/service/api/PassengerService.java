package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.exception.PassengerAlreadyRegisteredException;
import ru.tsystems.tsproject.sbb.exception.TrainAlreadyDepartedException;
import ru.tsystems.tsproject.sbb.exception.TrainAlreadyFullException;

import java.util.Collection;
import java.util.Date;

/**
 * interface for making client actions in system
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface PassengerService {

    /**
     * Finds all trains which are move from station $stationStart, departs to $stationEnd and trip time is between $start and $end
     *
     * @param  stationStartID
     *         ID of station at which train must depart
     *
     * @param  stationEndID
     *         ID of station at which train must arrive
     *
     * @param  start
     *         Date from which train must depart
     *
     * @param  end
     *         Date, to which train must arrive
     *
     * @throws ru.tsystems.tsproject.sbb.dao.DAOException
     *         If error occurred in JPA layer
     */
    public Collection<Train> findTrainsByStationsAndDate(int stationStartID, int stationEndID, Date start, Date end) throws DAOException;

    /**
     * Finds all trains, which have stop in station $station
     *
     * @param  stationID
     *         ID of station at which trains must be found
     *
     * @throws ru.tsystems.tsproject.sbb.dao.DAOException
     *         If error occurred in JPA layer
     */
    public Collection<Train> getTrainsByStation(int stationID) throws DAOException;

    /**
     * Create ticket for passenger $passenger on train $train
     *
     * @param  train
     *         Train in ticket
     *
     * @param  passenger
     *         Passenger in ticket
     *
     * @throws TrainAlreadyFullException
     *         If in train there are no free seats
     *
     * @throws PassengerAlreadyRegisteredException
     *         If current passenger had been already registered on current train
     *
     * @throws TrainAlreadyDepartedException
     *         If current train had been already departed
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Ticket purchaseTicket(Train train, Passenger passenger)
            throws TrainAlreadyFullException, PassengerAlreadyRegisteredException, TrainAlreadyDepartedException, DAOException;

    /**
     * Creates passenger, if passenger with specified document number not exists
     *
     * @param  passenger
     *         Passenger in ticket
     *
     * @throws PassengerAlreadyRegisteredException
     *         If current passenger had been already registered in system
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public void addPassenger(Passenger passenger) throws PassengerAlreadyRegisteredException, DAOException;
}
