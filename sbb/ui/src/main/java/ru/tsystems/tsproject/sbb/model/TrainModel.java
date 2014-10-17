package ru.tsystems.tsproject.sbb.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TimetableBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.service.impl.AdministratorServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.CommonServiceImpl;

import javax.persistence.EntityManager;
import java.util.*;

/**
 *
 * Class implements model behaviour of mvc pattern of object train
 * @author  Nikita Efremov
 * @since   1.0
 */

public class TrainModel extends AbstractModel {

    private static final Logger log = Logger.getLogger(TrainModel.class);

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
        EntityManager entityManager = null;
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            AdministratorService administratorService = new AdministratorServiceImpl(
                    stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Train train = new Train();
            train.setNumber(trainBean.getNumber());
            train.setSeats(Integer.parseInt(trainBean.getSeats()));
            train.setTotalSeats(Integer.parseInt(trainBean.getTotalSeats()));

            administratorService.addTrain(train);
            train = commonService.findTrain(train);

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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
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
        EntityManager entityManager = null;
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Train train = new Train();
            train.setNumber(trainBean.getNumber());

            train = commonService.findTrain(train);

            if (train == null) {
                throw new TrainNotExistsException("Train with number " + trainBean.getNumber() + " not exists");
            }

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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
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
        EntityManager entityManager = null;
        Collection<PassengerBean> passengerBeanSet = new LinkedList<PassengerBean>();
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
            AdministratorService administratorService = new AdministratorServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Train train = new Train();
            train.setId(trainBean.getId());
            train.setNumber(trainBean.getNumber());
            train = commonService.findTrain(train);

            if (train == null) {
                throw new TrainNotExistsException("Train with number " + trainBean.getNumber() + " not exists");
            }

            Collection<Passenger> passengers = administratorService.getPassengersByTrain(train);
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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
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
        EntityManager entityManager = null;
        Collection<PassengerBean> passengerBeanSet = new LinkedList<PassengerBean>();
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
            AdministratorService administratorService = new AdministratorServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Train train = new Train();
            train.setNumber(timetableBean.getTrainNumber());
            train = commonService.findTrain(train);

            if (train == null) {
                throw new TrainNotExistsException("Train with number " + timetableBean.getTrainNumber() + " not exists");
            }

            Set<Timetable> timetables = train.getTimetables();
            for (Timetable timetable: timetables) {
                if (timetable.getStation().getName().equals(timetableBean.getStationName())) {
                    throw new TrainStopAlreadyExistsException("Stop of the train with number " + timetableBean.getTrainNumber()
                            + " already exists on station with name " + timetableBean.getStationName());
                }
            }

            Station station = new Station();
            station.setName(timetableBean.getStationName());
            station = commonService.findStation(station);

            if (station == null) {
                throw new StationNotExistsException("Station with name " + timetableBean.getStationName() + " not exists");
            }

            Timetable timetable = new Timetable();
            timetable.setTrain(train);
            timetable.setStation(station);
            timetable.setDate(timetableBean.getDate());
            administratorService.addTimetable(timetable);

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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return timetableBean;
    }

    /**
     * Gets collection of all trains, which exist in system
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return collection of trains
     */
    public Collection<TrainBean> getAllTrains() {
        Collection<TrainBean> trainBeans = new ArrayList<TrainBean>();
        EntityManager entityManager = null;
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            AdministratorService administratorService = new AdministratorServiceImpl(
                    stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return trainBeans;
    }
}
