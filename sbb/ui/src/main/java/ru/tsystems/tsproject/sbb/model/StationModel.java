package ru.tsystems.tsproject.sbb.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TimetableBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.DAOException;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.exception.TrainNotExistsException;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.service.impl.AdministratorServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.CommonServiceImpl;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;


/**
 *
 * Class implements model behaviour of mvc pattern of object station
 * @author  Nikita Efremov
 * @since   1.0
 */
public class StationModel extends AbstractModel {
    private static final Logger log = Logger.getLogger(StationModel.class);

    /**
     * Adds new station with name, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  stationBean
     *         StationBean instance with default id value and specified name
     *
     * @return result of processing
     */
    public StationBean addStation(StationBean stationBean) {
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

            Station station = new Station();
            station.setName(stationBean.getName());

            administratorService.addStation(station);
            station = commonService.findStation(station);

            stationBean.setId(station.getId());
            stationBean.setName(station.getName());
        } catch (StationAlreadyExistsException e) {
            stationBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            stationBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            stationBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return stationBean;
    }

    /**
     * Searches station with name, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  stationBean
     *         TrainBean instance with default id value and specified name
     *
     * @return result of processing
     */
    public StationBean findStation(StationBean stationBean) {
        EntityManager entityManager = null;
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Station station = new Station();
            station.setName(stationBean.getName());

            station = commonService.findStation(station);

            if (station == null) {
                throw new StationNotExistsException("Station with name " + stationBean.getName() + " not exists");
            }

            stationBean.setId(station.getId());
            stationBean.setName(station.getName());
            Set<Timetable> timetableSet = station.getTimetables();
            Set<TimetableBean> timetableBeanSet = new TreeSet<TimetableBean>();
            for (Timetable timetable: timetableSet) {
                TimetableBean timetableBean = new TimetableBean();
                timetableBean.setId(timetable.getId());
                timetableBean.setDate(timetable.getDate());
                timetableBean.setStationName(timetable.getStation().getName());
                timetableBean.setTrainNumber(timetable.getTrain().getNumber());
                timetableBeanSet.add(timetableBean);
            }
            stationBean.setTimetables(timetableBeanSet);
        } catch (StationNotExistsException e) {
            stationBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (DAOException e) {
            stationBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            stationBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return stationBean;
    }

    /**
     * Gets collection of all stations, which exist in system
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return collection of stations
     */
    public Collection<StationBean> getAllStations() {
        Collection<StationBean> stationBeans = new ArrayList<StationBean>();
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
            Collection<Station> stations = administratorService.getAllStations();
            for (Station station: stations) {
                StationBean stationBean = new StationBean();
                stationBean.setId(station.getId());
                stationBean.setName(station.getName());
                stationBeans.add(stationBean);
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
        return stationBeans;
    }
}
