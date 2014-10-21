package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.PassengerNotRegisteredException;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.exception.TrainNotExistsException;
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
public class CommonServiceImpl implements CommonService {

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
        Station station = stationDAO.getStationByName(stationName);
        if (station == null) {
            throw new StationNotExistsException("Station with name " + stationName + " not exists");
        }
        return station;
    }

    public Train findTrain(String trainNumber) throws TrainNotExistsException, DAOException {
        Train train = trainDAO.getTrainByNumber(trainNumber);
        if (train == null) {
            throw new TrainNotExistsException("Train with number " + trainNumber + " not exists");
            }
        return train;
    }

    public Passenger findPassenger(String docNumber) throws DAOException, PassengerNotRegisteredException {
        Passenger passenger = passengerDAO.getPassengerByDocumentNumber(docNumber);
        if (passenger == null) {
            throw new PassengerNotRegisteredException("Passenger with document number " + docNumber + " is not registered");
        }
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
