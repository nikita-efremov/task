package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.dao.DAOException;

/**
 *
 * interface for making actions both for clients and administrators together
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface CommonService {

    /**
     * Searches station in system with using information, specified in param
     *
     * @param stationName
     *        Name of the station to search
     *
     * @return station with full info
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Station findStation(String stationName) throws DAOException;

    /**
     * Searches passenger in system with using information, specified in param
     *
     * @param docNumber
     *        Document number of passenger to search
     *
     * @return train with full info
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Passenger findPassenger(String docNumber) throws DAOException;

    /**
     * Searches train in system with using information, specified in param
     *
     * @param trainNumber
     *        Number of train to search
     *
     * @return train with full info
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Train findTrain(String trainNumber) throws DAOException;
}
