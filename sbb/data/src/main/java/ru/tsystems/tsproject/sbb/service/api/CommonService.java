package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.exception.PassengerNotExistsException;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.exception.TrainNotExistsException;

/**
 * interface for making actions both for clients and administrators together
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface CommonService {

    /**
     * Searches station in system with using information, specified in param. Method expects, that station exists
     *
     * @param stationName
     *        Name of the station to search
     *
     * @return station with full info
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     *
     * @throws StationNotExistsException
     *         If station not found
     */
    public Station findStation(String stationName) throws StationNotExistsException, DAOException;

    /**
     * Searches passenger in system with using information, specified in param. Method expects, that passenger exists
     *
     * @param docNumber
     *        Document number of passenger to search
     *
     * @return train with full info
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     *
     * @throws PassengerNotExistsException
     *         if passenger is not found
     */
    public Passenger findPassenger(String docNumber) throws PassengerNotExistsException, DAOException;

    /**
     * Searches train in system with using information, specified in param. Method expects, that train exists
     *
     * @param trainNumber
     *        Number of train to search
     *
     * @return train with full info
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     *
     * @throws TrainNotExistsException
     *         if train not found in system
     */
    public Train findTrain(String trainNumber) throws TrainNotExistsException, DAOException;
}
