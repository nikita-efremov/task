package ru.tsystems.tsproject.sbb.serviceImpl;

import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.daoImpl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.service.AdministratorService;

import java.util.Collection;

/**
 *
 * AdministratorService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AdministratorServiceImpl implements AdministratorService {
    private StationDAO stationDAO;

    public AdministratorServiceImpl() {
        stationDAO = new StationDAOImpl();
    }

    public void addStation(Station station) throws StationAlreadyExistsException {
        if (stationDAO.getStationByName(station.getName()) == null) {
            stationDAO.addStation(station);
        } else {
            throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
        }
    }

    public void addTrain(Train train) {
        //
    }

    public Collection<Passenger> getPassengersByTrain(Train train) {
        return null;
    }

    public Collection<Train> getAllTrains() {
        return null;
    }

    public Collection<Station> getAllStations() {
        return stationDAO.getAllStations();
    }
}
