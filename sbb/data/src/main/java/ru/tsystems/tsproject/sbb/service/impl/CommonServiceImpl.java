package ru.tsystems.tsproject.sbb.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.PassengerNotExistsException;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.exception.SystemException;
import ru.tsystems.tsproject.sbb.exception.TrainNotExistsException;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.dao.DAOException;

/**
 *
 * CommonService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
@Service
@Transactional(readOnly = true)
public class CommonServiceImpl implements CommonService {

    private static final Logger log = Logger.getLogger(CommonServiceImpl.class);

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private PassengerDAO passengerDAO;

    public Station findStation(String stationName) throws StationNotExistsException {
        try {
            log.info("Start search station with name: " + stationName);
            Station station = stationDAO.getStationByName(stationName);
            if (station == null) {
                throw new StationNotExistsException("Station with name " + stationName + " not exists");
            }
            log.info("Station found: " + station);
            return station;
        } catch (DAOException e) {
            throw convertDAOException(e);
        }
    }

    public Train findTrain(String trainNumber) throws TrainNotExistsException {
        try {
            log.info("Start search train with number: " + trainNumber);
            Train train = trainDAO.getTrainByNumber(trainNumber);
            if (train == null) {
                throw new TrainNotExistsException("Train with number " + trainNumber + " not exists");
            }
            log.info("Train found: " + train);
            return train;
        } catch (DAOException e) {
            throw convertDAOException(e);
        }
    }

    public Passenger findPassenger(String docNumber) throws PassengerNotExistsException {
        try {
            log.info("Start search passenger with document number: " + docNumber);
            Passenger passenger = passengerDAO.getPassengerByDocumentNumber(docNumber);
            if (passenger == null) {
                throw new PassengerNotExistsException("Passenger with document number " + docNumber + " is not registered");
            }
            log.info("Passenger found " + passenger);
            return passenger;
        } catch (DAOException e) {
           throw convertDAOException(e);
        }
    }

    /**
     * Method converts DAOException, which can occur in data access layer, to SystemException,
     * with saving cause, message and error code
     * @param e
     *        DAOException to convert
     * @return SystemException
     *        Result of converting
     */
    public SystemException convertDAOException(DAOException e) {
        SystemException systemException = new SystemException(e.getMessage());
        systemException.setErrorCode(e.getErrorCode());
        systemException.initCause(e.getCause());
        return systemException;
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
