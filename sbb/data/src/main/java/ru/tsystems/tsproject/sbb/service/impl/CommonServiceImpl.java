package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.dao.impl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * CommonService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class CommonServiceImpl extends AbstractServiceImpl implements CommonService {

    public CommonServiceImpl(StationDAO stationDAO) {
        super(stationDAO);
    }

    public Station getStationInfo(Station station) throws DAOException {
        Station stationWithFullInfo = null;
        if (station.getId() > 0) {
           stationWithFullInfo = getStationDAO().getStationById(station.getId());
        } else if (!station.getName().equals("")) {
            stationWithFullInfo = getStationDAO().getStationByName(station.getName());
        }
        return stationWithFullInfo;
    }
}
