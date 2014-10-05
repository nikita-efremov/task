package ru.tsystems.tsproject.sbb.serviceImpl;

import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.daoImpl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.service.CommonService;

/**
 *
 * CommonService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class CommonServiceImpl implements CommonService {

    public Station getStationInfo(Station station) {
        Station stationWithFullInfo = null;
        StationDAO stationDAO = new StationDAOImpl();
        if (station.getId() > 0) {
           stationWithFullInfo = stationDAO.getStationById(station.getId());
        } else if (!station.getName().equals("")) {
            stationWithFullInfo = stationDAO.getStationByName(station.getName());
        }
        return stationWithFullInfo;
    }
}
