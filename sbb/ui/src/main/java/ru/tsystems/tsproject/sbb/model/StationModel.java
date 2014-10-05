package ru.tsystems.tsproject.sbb.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.service.AdministratorService;
import ru.tsystems.tsproject.sbb.service.CommonService;
import ru.tsystems.tsproject.sbb.serviceImpl.AdministratorServiceImpl;
import ru.tsystems.tsproject.sbb.serviceImpl.CommonServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;


/**
 *
 * Class implements model behaviour of mvc pattern
 * @author  Nikita Efremov
 * @since   1.0
 */
public class StationModel {
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
        try {
            AdministratorService administratorService = new AdministratorServiceImpl();
            CommonService commonService = new CommonServiceImpl();

            Station station = new Station();
            station.setName(stationBean.getName());

            administratorService.addStation(station);
            station = commonService.getStationInfo(station);

            stationBean.setId(station.getId());
            stationBean.setName(station.getName());
        } catch (StationAlreadyExistsException e) {
            stationBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
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
     * @return collection of stations
     */
    public Collection<StationBean> getAllStations() {
        Collection<StationBean> stationBeans = new ArrayList<StationBean>();
        try {
            AdministratorService administratorService = new AdministratorServiceImpl();
            Collection<Station> stations = administratorService.getAllStations();
            for (Station station: stations) {
                StationBean stationBean = new StationBean();
                stationBean.setId(station.getId());
                stationBean.setName(station.getName());
                stationBean.setTimetables(station.getTimetables());
                stationBeans.add(stationBean);
            }
        } catch (Exception e) {
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return stationBeans;
    }
}
