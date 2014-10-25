package ru.tsystems.tsproject.sbb.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.TimetableBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;
import ru.tsystems.tsproject.sbb.service.impl.AdministratorServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.CommonServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.PassengerServiceImpl;

import javax.persistence.EntityManager;
import java.util.*;

/**
 *
 * Class implements model behaviour of mvc pattern of object train
 * @author  Nikita Efremov
 * @since   1.0
 */

public class TrainModel {

    private static final Logger log = Logger.getLogger(TrainModel.class);

    private AdministratorService administratorService;
    private PassengerService passengerService;
    private CommonService commonService;

    public TrainModel(AdministratorService administratorService, PassengerService passengerService, CommonService commonService) {
        this.administratorService = administratorService;
        this.passengerService = passengerService;
        this.commonService = commonService;
    }

    /**
     * Adds new train with number, seats and totalSeats specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  trainBean
     *         TrainBean instance with default id value and specified name
     *
     * @return result of processing
     */
    public TrainBean addTrain(TrainBean trainBean) {
        try {
            Train train = new Train();
            train.setNumber(trainBean.getNumber());
            train.setSeats(Integer.parseInt(trainBean.getSeats()));
            train.setTotalSeats(Integer.parseInt(trainBean.getTotalSeats()));

            train = administratorService.addTrain(train);

            trainBean.setId(train.getId());
            trainBean.setNumber(train.getNumber());
            trainBean.setSeats(String.valueOf(train.getSeats()));
            trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
        } catch (TrainAlreadyExistsException e) {
            trainBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            trainBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            trainBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return trainBean;
    }

    /**
     * Searches train with number, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  trainBean
     *         TrainBean instance with default id value and specified name
     *
     * @return result of processing
     */
    public TrainBean findTrain(TrainBean trainBean) {
        try {
            Train train = commonService.findTrain(trainBean.getNumber());

            trainBean.setId(train.getId());
            trainBean.setNumber(train.getNumber());
            trainBean.setSeats(String.valueOf(train.getSeats()));
            trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
            Set<Timetable> timetableSet = train.getTimetables();
            Set<TimetableBean> timetableBeanSet = new TreeSet<TimetableBean>();
            for (Timetable timetable: timetableSet) {
                TimetableBean timetableBean = new TimetableBean();
                timetableBean.setId(timetable.getId());
                timetableBean.setDate(timetable.getDate());
                timetableBean.setStationName(timetable.getStation().getName());
                timetableBean.setTrainNumber(timetable.getTrain().getNumber());
                timetableBeanSet.add(timetableBean);
            }
            trainBean.setTimetables(timetableBeanSet);
        } catch (TrainNotExistsException e) {
            trainBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (DAOException e) {
            trainBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            trainBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return trainBean;
    }

    /**
     * Searches passengers of train with number, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  trainBean
     *         TrainBean instance with default id value and specified name
     *
     * @return result of processing
     */
    public Collection<PassengerBean> findTrainPassengers(TrainBean trainBean) {
        Collection<PassengerBean> passengerBeanSet = new LinkedList<PassengerBean>();
        try {
            Collection<Passenger> passengers = administratorService.getPassengersByTrain(trainBean.getNumber());
            for (Passenger passenger: passengers) {
                PassengerBean passengerBean = new PassengerBean();
                passengerBean.setLastName(passenger.getLastName());
                passengerBean.setFirstName(passenger.getFirstName());
                passengerBean.setDocNumber(passenger.getDocNumber());
                passengerBean.setBirthDate(passenger.getBirthDate());
                passengerBeanSet.add(passengerBean);
            }
        } catch (TrainNotExistsException e) {
            trainBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (DAOException e) {
            trainBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            trainBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return passengerBeanSet;
    }

    /**
     * Add stop for specified train on specified station on specified date
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  timetableBean
     *         TrainBean instance with default id value and specified name
     *
     * @return result of processing
     */
    public TimetableBean addTrainStop(TimetableBean timetableBean) {
        try {
            administratorService.addTimetable(timetableBean.getTrainNumber(), timetableBean.getStationName(), timetableBean.getDate());
        } catch (StationNotExistsException e) {
            timetableBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (TrainNotExistsException e) {
            timetableBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (TrainStopAlreadyExistsException e) {
            timetableBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (DAOException e) {
            timetableBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            timetableBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return timetableBean;
    }

    /**
     * Gets collection of trains, which have stops on specified stations and specified dates
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return collection of trains
     */
    public Collection<TrainBean> findTrainsByStationsAndDate(TimetableBean startBean, TimetableBean endBean) {
        Collection<TrainBean> trainBeans = new ArrayList<TrainBean>();
        try {
            Collection<Train> trains = passengerService.findTrainsByStationsAndDate(
                    startBean.getStationName(), endBean.getStationName(), startBean.getDate(), endBean.getDate());
            for (Train train: trains) {
                TrainBean trainBean = new TrainBean();
                trainBean.setId(train.getId());
                trainBean.setNumber(train.getNumber());
                trainBean.setSeats(String.valueOf(train.getSeats()));
                trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
                trainBeans.add(trainBean);
            }
        } catch (StationNotExistsException e) {
            startBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (DAOException e) {
            startBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            startBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return trainBeans;
    }

    /**
     * Gets collection of all trains, which exist in system
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return collection of trains
     */
    public Collection<TrainBean> getAllTrains() {
        Collection<TrainBean> trainBeans = new ArrayList<TrainBean>();
        try {
            Collection<Train> trains = administratorService.getAllTrains();
            for (Train train: trains) {
                TrainBean trainBean = new TrainBean();
                trainBean.setId(train.getId());
                trainBean.setNumber(train.getNumber());
                trainBean.setSeats(String.valueOf(train.getSeats()));
                trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
                trainBeans.add(trainBean);
            }
        } catch (DAOException e) {
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return trainBeans;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public void setAdministratorService(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }
}
