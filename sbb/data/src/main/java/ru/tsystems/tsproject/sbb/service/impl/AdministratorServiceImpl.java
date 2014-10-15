package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.exception.TrainAlreadyExistsException;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;

/**
 *
 * AdministratorService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AdministratorServiceImpl extends AbstractServiceImpl implements AdministratorService {

    public AdministratorServiceImpl(StationDAO stationDAO,
                                    TrainDAO trainDAO,
                                    PassengerDAO passengerDAO,
                                    TimetableDAO timetableDAO,
                                    TicketDAO ticketDAO) {
        super(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
    }

    public void addStation(Station station) throws StationAlreadyExistsException, DAOException {
        if (getStationDAO().getStationByName(station.getName()) == null) {
            getStationDAO().addStation(station);
        } else {
            throw new StationAlreadyExistsException("Station with name " + station.getName() + " already exists");
        }
    }

    public void addTrain(Train train) throws TrainAlreadyExistsException, DAOException {
        if (getTrainDAO().getTrainByNumber(train.getNumber()) == null) {
            getTrainDAO().addTrain(train);
        } else {
            throw new TrainAlreadyExistsException("Train with number " + train.getNumber() + " already exists");
        }
    }

    public Collection<Passenger> getPassengersByTrain(Train train) throws DAOException {
        return getPassengerDAO().getPassengersByTrain(train.getId());
    }

    public Collection<Train> getAllTrains() throws DAOException {
        return getTrainDAO().getAllTrains();
    }

    public Collection<Station> getAllStations() throws DAOException {
        return getStationDAO().getAllStations();
    }
}
