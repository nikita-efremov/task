package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
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
     * Finds all trains which have stop specified station
     *
     * @param  stationName
     *         Name of station at which train must have stop
     *
     * @throws StationNotExistsException
     *         if station not found
     */
    public Collection<Train> findTrainsByStation(String stationName)
            throws StationNotExistsException;

    /**
     * Finds all trains which are move from stationStart, departs to stationEnd and trip time is between start and end
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
     */
    public Collection<Train> findTrainsByStationsAndDate(String stationStartName, String stationEndName, Date start, Date end)
            throws StationNotExistsException;

    /**
     * Create ticket for specified passenger on specified train
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
     * @throws PassengerNotExistsException
     *         If passenger not found
     *
     * @throws TrainAlreadyFullException
     *         If in train there are no free seats
     *
     * @throws PassengerAlreadyRegisteredOnTrainException
     *         If current passenger had been already registered on current train
     *
     * @throws TrainAlreadyDepartedException
     *         If current train had been already departed
     *
     * @throws TicketAlreadyExistsException
     *         If current ticket already purchased
     */
    public Ticket purchaseTicket(String trainNumber, String docNumber) throws
            TrainNotExistsException, PassengerNotExistsException, TrainAlreadyFullException,
            PassengerAlreadyRegisteredOnTrainException, TrainAlreadyDepartedException,
            TicketAlreadyExistsException;

    /**
     * Creates passenger, if passenger with specified document number not exists
     *
     * @param  passenger
     *         Passenger in ticket
     *
     * @throws PassengerAlreadyExistsException
     *         If current passenger had been already registered in system
     *
     */
    public Passenger addPassenger(Passenger passenger) throws PassengerAlreadyExistsException;
}
