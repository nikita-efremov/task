package ru.tsystems.tsproject.sbb.controller.helpers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.tsproject.sbb.viewbean.*;
import ru.tsystems.tsproject.sbb.exception.SystemException;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

import java.util.*;

/**
 * Part of a controller which launches appropriate service method, related to train,
 * and makes mapping from view bean class to service bean class and conversely
 * @author  Nikita Efremov
 * @since   1.0
 */
@Component
public class TrainControllersHelper {

    private static final Logger log = Logger.getLogger(TrainControllersHelper.class);

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private PassengerService passengerService;

    /**
     * Adds new train with number, seats and totalSeats specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  trainBean
     *         TrainViewBean instance with default id value and specified name
     *
     * @return TrainViewBean
     *         result of processing
     */
    public TrainViewBean addTrain(TrainViewBean trainBean) {
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
        } catch (SystemException e) {
            trainBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
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
     *         TrainViewBean instance with default id value and specified name
     *
     * @return TrainViewBean
     *         result of processing
     */
    public TrainViewBean findTrain(TrainViewBean trainBean) {
        try {
            Train train = administratorService.findTrain(trainBean.getNumber());

            trainBean.setId(train.getId());
            trainBean.setNumber(train.getNumber());
            trainBean.setSeats(String.valueOf(train.getSeats()));
            trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
            Set<Timetable> timetableSet = train.getTimetables();
            Set<TimetableViewBean> timetableBeanSet = new TreeSet<TimetableViewBean>();
            for (Timetable timetable: timetableSet) {
                TimetableViewBean timetableBean = new TimetableViewBean();
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
        } catch (SystemException e) {
            trainBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
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
     *         TrainViewBean instance with default id value and specified name
     *
     * @return Collection<PassengerViewBean>
     *         result of processing
     */
    public Collection<PassengerViewBean> findTrainPassengers(TrainViewBean trainBean) {
        Collection<PassengerViewBean> passengerBeanSet = new LinkedList<PassengerViewBean>();
        try {
            Collection<Passenger> passengers = administratorService.getPassengersByTrain(trainBean.getNumber());
            for (Passenger passenger: passengers) {
                PassengerViewBean passengerBean = new PassengerViewBean();
                passengerBean.setId(passenger.getId());
                passengerBean.setLastName(passenger.getLastName());
                passengerBean.setFirstName(passenger.getFirstName());
                passengerBean.setDocNumber(passenger.getDocNumber());
                passengerBean.setBirthDate(passenger.getBirthDate());
                passengerBeanSet.add(passengerBean);
            }
        } catch (TrainNotExistsException e) {
            trainBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (SystemException e) {
            trainBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
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
     *         TrainViewBean instance with default id value and specified name
     *
     * @return TimetableViewBean
     *         result of processing
     */
    public TimetableViewBean addTrainStop(TimetableViewBean timetableBean) {
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
        } catch (SystemException e) {
            timetableBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            timetableBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return timetableBean;
    }

    /**
     * Gets collection of trains, which have stop on specified station
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return Collection<TrainViewBean>
     *         collection of trains
     */
    public Collection<TrainViewBean> findTrainsByStation(StationViewBean stationBean) {
        Collection<TrainViewBean> trainBeans = new ArrayList<TrainViewBean>();
        try {
            Collection<Train> trains = passengerService.findTrainsByStation(stationBean.getName());
            for (Train train: trains) {
                TrainViewBean trainBean = new TrainViewBean();
                trainBean.setId(train.getId());
                trainBean.setNumber(train.getNumber());
                trainBean.setSeats(String.valueOf(train.getSeats()));
                trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
                trainBeans.add(trainBean);
            }
        } catch (StationNotExistsException e) {
            stationBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (SystemException e) {
            stationBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            stationBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return trainBeans;
    }

    /**
     * Gets collection of trains, which have stops on specified stations and specified dates
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return Collection<TrainViewBean>
     *         collection of trains
     */
    public Collection<TrainViewBean> findTrainsByStationsAndDate(ComplexTrainSearchViewBean complexTrainSearchBean) {
        Collection<TrainViewBean> trainBeans = new ArrayList<TrainViewBean>();
        try {
            Collection<Train> trains = passengerService.findTrainsByStationsAndDate(
                    complexTrainSearchBean.getStationStartName(),
                    complexTrainSearchBean.getStationEndName(),
                    complexTrainSearchBean.getStartDate(),
                    complexTrainSearchBean.getEndDate());
            for (Train train: trains) {
                TrainViewBean trainBean = new TrainViewBean();
                trainBean.setId(train.getId());
                trainBean.setNumber(train.getNumber());
                trainBean.setSeats(String.valueOf(train.getSeats()));
                trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
                trainBeans.add(trainBean);
            }
        } catch (StationNotExistsException e) {
            complexTrainSearchBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (SystemException e) {
            complexTrainSearchBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            complexTrainSearchBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return trainBeans;
    }

    /**
     * Gets collection of all trains, which exist in system
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return Collection<TrainViewBean>
     *         collection of trains
     */
    public Collection<TrainViewBean> getAllTrains() {
        Collection<TrainViewBean> trainBeans = new ArrayList<TrainViewBean>();
        try {
            Collection<Train> trains = administratorService.getAllTrains();
            for (Train train: trains) {
                TrainViewBean trainBean = new TrainViewBean();
                trainBean.setId(train.getId());
                trainBean.setNumber(train.getNumber());
                trainBean.setSeats(String.valueOf(train.getSeats()));
                trainBean.setTotalSeats(String.valueOf(train.getTotalSeats()));
                trainBeans.add(trainBean);
            }
        } catch (SystemException e) {
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
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
}
