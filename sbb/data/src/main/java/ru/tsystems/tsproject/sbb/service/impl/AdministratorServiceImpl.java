package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.dao.impl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

/**
 *
 * AdministratorService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AdministratorServiceImpl extends AbstractServiceImpl implements AdministratorService {

    public AdministratorServiceImpl(StationDAO stationDAO) {
        super(stationDAO);
    }

    public void addStation(Station station) throws StationAlreadyExistsException, DAOException {
        if (getStationDAO().getStationByName(station.getName()) == null) {
            getStationDAO().addStation(station);
        } else {
            throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
        }
    }

    public void addTrain(Train train) throws DAOException {
        //
    }

    public Collection<Passenger> getPassengersByTrain(Train train) throws DAOException {
        return null;
    }

    public Collection<Train> getAllTrains() throws DAOException {
        return null;
    }

    public Collection<Station> getAllStations() throws DAOException {
        return getStationDAO().getAllStations();
    }
}
