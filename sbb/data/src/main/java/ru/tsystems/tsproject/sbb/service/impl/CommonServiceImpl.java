package ru.tsystems.tsproject.sbb.service.impl;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.PassengerNotExistsException;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.exception.TrainNotExistsException;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.dao.DAOException;

/**
 *
 * CommonService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class CommonServiceImpl implements CommonService {

    private static final Logger log = Logger.getLogger(CommonServiceImpl.class);

    private StationDAO stationDAO;
    private TrainDAO trainDAO;
    private PassengerDAO passengerDAO;

    public CommonServiceImpl(StationDAO stationDAO,
                             TrainDAO trainDAO,
                             PassengerDAO passengerDAO) {
        this.stationDAO = stationDAO;
        this.trainDAO = trainDAO;
        this.passengerDAO = passengerDAO;
    }

    public Station findStation(String stationName) throws StationNotExistsException, DAOException {
        log.info("Start search station with name: " + stationName);
        Station station = stationDAO.getStationByName(stationName);
        if (station == null) {
            throw new StationNotExistsException("Station with name " + stationName + " not exists");
        }
        log.info("Station found: " + station);
        return station;
    }

    public Train findTrain(String trainNumber) throws TrainNotExistsException, DAOException {
        log.info("Start search train with number: " + trainNumber);
        Train train = trainDAO.getTrainByNumber(trainNumber);
        if (train == null) {
            throw new TrainNotExistsException("Train with number " + trainNumber + " not exists");
        }
        log.info("Train found: " + train);
        return train;
    }

    public Passenger findPassenger(String docNumber) throws DAOException, PassengerNotExistsException {
        log.info("Start search passenger with document number: " + docNumber);
        Passenger passenger = passengerDAO.getPassengerByDocumentNumber(docNumber);
        if (passenger == null) {
            throw new PassengerNotExistsException("Passenger with document number " + docNumber + " is not registered");
        }
        log.info("Passenger found " + passenger);
        return passenger;
    }

    public StationDAO getStationDAO() {
        return stationDAO;
    }

    public void setStationDAO(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public TrainDAO getTrainDAO() {
        return trainDAO;
    }

    public void setTrainDAO(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    public PassengerDAO getPassengerDAO() {
        return passengerDAO;
    }

    public void setPassengerDAO(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }
}
