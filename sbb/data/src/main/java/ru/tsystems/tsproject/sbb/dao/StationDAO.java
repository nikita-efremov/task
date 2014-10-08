package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
public interface StationDAO {

    public void addStation(Station station) throws DAOException;
    public Station getStationById(int stationID) throws DAOException;
    public Station getStationByName(String name) throws DAOException;
    public Collection getAllStations() throws DAOException;
    public void updateStation(Station station) throws DAOException;
    public void deleteStation(Station station) throws DAOException;
}
