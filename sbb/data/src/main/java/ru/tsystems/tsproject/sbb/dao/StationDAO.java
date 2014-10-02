package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Station;

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
    public void updateStation(Station station);
    public void deleteStation(Station station);
}
