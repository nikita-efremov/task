package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.LinkedList;
import java.util.List;

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

    public Station findStation(String stationName) throws DAOException {
        return getStationDAO().getStationByName(stationName);
    }

    public Train findTrain(String trainNumber) throws DAOException {
        return getTrainDAO().getTrainByNumber(trainNumber);
    }

    public Passenger findPassenger(String docNumber) throws DAOException {
        return getPassengerDAO().getPassengerByDocumentNumber(docNumber);
    }
}
