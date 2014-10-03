package ru.tsystems.tsproject.sbb.serviceImpl;

import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.daoImpl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.service.AdministratorService;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 15:28
 * To change this template use File | Settings | File Templates.
 */
public class AdministratorServiceImpl implements AdministratorService {
    private StationDAO stationDAO;

    public AdministratorServiceImpl() {
        stationDAO = new StationDAOImpl();
    }

    public void addStation(Station station) {
        if (stationDAO.getStationByName(station.getName()) == null) {
            stationDAO.addStation(station);
        }
    }

    public void addTrain(Collection<Timetable> timetables) {
        //
    }

    public Collection<Passenger> getPassengersByTrain(Train train) {
        return null;
    }

    public Collection<Train> getAllTrains() {
        return null;
    }
}
