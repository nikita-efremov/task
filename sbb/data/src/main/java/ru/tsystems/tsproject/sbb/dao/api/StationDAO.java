package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.dao.DAOException;

public interface StationDAO extends CommonDAO <Station> {

    public Station getStationByName(String name) throws DAOException;
}
