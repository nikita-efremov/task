package ru.tsystems.tsproject.sbb.controller.helpers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.tsproject.sbb.viewbean.StationViewBean;
import ru.tsystems.tsproject.sbb.viewbean.TimetableViewBean;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.exception.SystemException;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;


/**
 * Part of a controller which launches appropriate service method, related to station,
 * and makes mapping from view bean class to service bean class and conversely
 * @author  Nikita Efremov
 * @since   1.0
 */
@Component
public class StationControllersHelper {
    private static final Logger log = Logger.getLogger(StationControllersHelper.class);

    @Autowired
    private AdministratorService administratorService;

    /**
     * Adds new station with name, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  stationBean
     *         StationViewBean instance with default id value and specified name
     *
     * @return StationViewBean
     *         result of processing
     */
    public StationViewBean addStation(StationViewBean stationBean) {
        try {
            Station station = new Station();
            station.setName(stationBean.getName());

            station = administratorService.addStation(station);

            stationBean.setId(station.getId());
            stationBean.setName(station.getName());
        } catch (StationAlreadyExistsException e) {
            stationBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (SystemException e) {
            stationBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            stationBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return stationBean;
    }

    /**
     * Searches station with name, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  stationBean
     *         TrainViewBean instance with default id value and specified name
     *
     * @return StationViewBean
     *         result of processing
     */
    public StationViewBean findStation(StationViewBean stationBean) {
        try {
            Station station = new Station();
            station.setName(stationBean.getName());

            station = administratorService.findStation(station.getName());

            stationBean.setId(station.getId());
            stationBean.setName(station.getName());
            Set<Timetable> timetableSet = station.getTimetables();
            Set<TimetableViewBean> timetableBeanSet = new TreeSet<TimetableViewBean>();
            for (Timetable timetable: timetableSet) {
                TimetableViewBean timetableBean = new TimetableViewBean();
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
        } catch (SystemException e) {
            stationBean.setProcessingErrorMessage("System error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            stationBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return stationBean;
    }

    /**
     * Gets collection of all stations, which exist in system
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return Collection<StationViewBean>
     *         collection of stations
     */
    public Collection<StationViewBean> getAllStations() {
        Collection<StationViewBean> stationBeans = new ArrayList<StationViewBean>();
        try {
            Collection<Station> stations = administratorService.getAllStations();
            for (Station station: stations) {
                StationViewBean stationBean = new StationViewBean();
                stationBean.setId(station.getId());
                stationBean.setName(station.getName());
                stationBeans.add(stationBean);
            }
        } catch (SystemException e) {
            log.log(Level.ERROR, "System error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return stationBeans;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public void setAdministratorService(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
}
