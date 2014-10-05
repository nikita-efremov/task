package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Station;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
public interface StationDAO {

    public void addStation(Station station);
    public Station getStationById(int stationID);
    public Station getStationByName(String name);
    public Collection getAllStations();
    public void updateStation(Station station);
    public void deleteStation(Station station);
}
