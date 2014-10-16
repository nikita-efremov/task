package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;

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
     * @param station
     *        Station instance with specified id or name
     *
     * @return station with full info
     *
     * @throws DAOException
     *         If error occurred in JPA layer
     */
    public Station findStation(Station station) throws DAOException;
}
