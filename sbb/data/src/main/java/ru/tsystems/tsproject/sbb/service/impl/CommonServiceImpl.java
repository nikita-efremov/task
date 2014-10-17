package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
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

    public CommonServiceImpl(StationDAO stationDAO,
                             TrainDAO trainDAO,
                             PassengerDAO passengerDAO,
                             TimetableDAO timetableDAO,
                             TicketDAO ticketDAO) {
        super(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
    }

    public Station findStation(Station station) throws DAOException {
        Station stationWithFullInfo = null;
        if (station.getId() > 0) {
           stationWithFullInfo = getStationDAO().getStationById(station.getId());
        } else if (!station.getName().equals("")) {
            stationWithFullInfo = getStationDAO().getStationByName(station.getName());
        }
        return stationWithFullInfo;
    }

    public Train findTrain(Train train) throws DAOException {
        Train trainWithFullInfo = null;
        if (train.getId() > 0) {
            trainWithFullInfo = getTrainDAO().getTrainByID(train.getId());
        } else if (!train.getNumber().equals("")) {
            trainWithFullInfo = getTrainDAO().getTrainByNumber(train.getNumber());
        }
        return trainWithFullInfo;
    }
}
