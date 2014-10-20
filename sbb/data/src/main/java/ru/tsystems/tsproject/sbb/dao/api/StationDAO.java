package ru.tsystems.tsproject.sbb.dao.api;

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
public interface StationDAO extends CommonDAO <Station> {

    public Station getStationByName(String name) throws DAOException;
}
