package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.dao.DAOException;

/**
 * DAO-class for entity Station. Extends CommonDAO interface with wildcard class = Station
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface StationDAO extends CommonDAO <Station> {

    /**
     * Get operation: Station object will be got by its unique field stationName
     * @param name
     * Station`s name
     * @return Station
     * Found object in database
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public Station getStationByName(String name) throws DAOException;
}
