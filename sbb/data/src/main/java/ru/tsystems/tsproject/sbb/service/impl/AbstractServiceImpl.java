package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.StationDAO;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 15.10.14
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractServiceImpl {
    private StationDAO stationDAO;

    public AbstractServiceImpl(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public StationDAO getStationDAO() {
        return stationDAO;
    }

    public void setStationDAO(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }
}
