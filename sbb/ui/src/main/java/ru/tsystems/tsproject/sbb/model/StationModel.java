package ru.tsystems.tsproject.sbb.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.dao.impl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.exception.DAOException;
import ru.tsystems.tsproject.sbb.exception.StationAlreadyExistsException;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.service.impl.AdministratorServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.CommonServiceImpl;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * Class implements model behaviour of mvc pattern
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
        try {
            EntityManager entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            AdministratorService administratorService = new AdministratorServiceImpl(stationDAO);
            CommonService commonService = new CommonServiceImpl(stationDAO);

            Station station = new Station();
            station.setName(stationBean.getName());

            administratorService.addStation(station);
            station = commonService.getStationInfo(station);

            stationBean.setId(station.getId());
            stationBean.setName(station.getName());
        } catch (StationAlreadyExistsException e) {
            stationBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            stationBean.setProcessingErrorMessage("Database error occurred");
            log.log(Level.ERROR, "Database error occurred: " + e);
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
            EntityManager entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            AdministratorService administratorService = new AdministratorServiceImpl(stationDAO);
            Collection<Station> stations = administratorService.getAllStations();
            for (Station station: stations) {
                StationBean stationBean = new StationBean();
                stationBean.setId(station.getId());
                stationBean.setName(station.getName());
                stationBean.setTimetables(station.getTimetables());
                stationBeans.add(stationBean);
            }
        } catch (DAOException e) {
            log.log(Level.ERROR, "Database error occurred: " + e);
        } catch (Exception e) {
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return stationBeans;
    }
}
