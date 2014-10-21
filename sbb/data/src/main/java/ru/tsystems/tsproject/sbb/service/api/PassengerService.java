package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.exception.*;

import java.util.Collection;
import java.util.Date;

/**
 * interface for making client actions in system
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface PassengerService extends CommonService {

    /**
     * Finds all trains which are move from station $stationStart, departs to $stationEnd and trip time is between $start and $end
     *
     * @param  stationStartName
     *         Name of station at which train must depart
     *
     * @param  stationEndName
     *         Name of station at which train must arrive
     *
     * @param  start
     *         Date from which train must depart
     *
     * @param  end
     *         Date, to which train must arrive
     *
     * @throws StationNotExistsException
     *         if station not found
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Collection<Train> findTrainsByStationsAndDate(String stationStartName, String stationEndName, Date start, Date end)
            throws StationNotExistsException, DAOException;

    /**
     * Create ticket for passenger $passenger on train $train
     *
     * @param  trainNumber
     *         Train number in ticket
     *
     * @param  docNumber
     *         Document number of passenger in ticket
     *
     * @throws TrainNotExistsException
     *         If train not found
     *
     * @throws PassengerNotRegisteredException
     *         If passenger not found
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
    public Ticket purchaseTicket(String trainNumber, String docNumber)
            throws TrainNotExistsException, PassengerNotRegisteredException, TrainAlreadyFullException, PassengerAlreadyRegisteredException, TrainAlreadyDepartedException, DAOException;

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
    public Passenger addPassenger(Passenger passenger) throws PassengerAlreadyRegisteredException, DAOException;
}
