package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.dao.DAOTransactionManager;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.model.PassengerModel;
import ru.tsystems.tsproject.sbb.model.StationModel;
import ru.tsystems.tsproject.sbb.model.TrainModel;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;
import ru.tsystems.tsproject.sbb.service.impl.AdministratorServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.CommonServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.PassengerServiceImpl;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Class which contains objects, which used by on request processing. All objects are created lazily and only once
 * @author  Nikita Efremov
 * @since   1.0
 */
public class ApplicationContext {

    private static volatile EntityManager entityManager;
    private static volatile DAOTransactionManager daoTransactionManager;

    private static volatile TrainDAO trainDAO;
    private static volatile StationDAO stationDAO;
    private static volatile PassengerDAO passengerDAO;
    private static volatile TicketDAO ticketDAO;
    private static volatile TimetableDAO timetableDAO;

    private static volatile CommonService commonService;
    private static volatile AdministratorService administratorService;
    private static volatile PassengerService passengerService;

    private static volatile TrainModel trainModel;
    private static volatile StationModel stationModel;
    private static volatile PassengerModel passengerModel;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            synchronized (ApplicationContext.class) {
                if (entityManager == null) {
                    entityManager = Persistence.createEntityManagerFactory("jpa-hibernate").createEntityManager();
                }
            }
        }
        return entityManager;
    }

    public static DAOTransactionManager getDaoTransactionManager() {
        if (daoTransactionManager == null) {
            synchronized (ApplicationContext.class) {
                if (daoTransactionManager == null) {
                    daoTransactionManager = new DAOTransactionManager(getEntityManager());
                }
            }
        }
        return daoTransactionManager;
    }

    public static TrainDAO getTrainDAO() {
        if (trainDAO == null) {
            synchronized (ApplicationContext.class) {
                if (trainDAO == null) {
                    trainDAO = new TrainDAOImpl(getEntityManager());
                }
            }
        }
        return trainDAO;
    }

    public static StationDAO getStationDAO() {
        if (stationDAO == null) {
            synchronized (ApplicationContext.class) {
                if (stationDAO == null) {
                    stationDAO = new StationDAOImpl(getEntityManager());
                }
            }
        }
        return stationDAO;
    }

    public static PassengerDAO getPassengerDAO() {
        if (passengerDAO == null) {
            synchronized (ApplicationContext.class) {
                if (passengerDAO == null) {
                    passengerDAO = new PassengerDAOImpl(getEntityManager());
                }
            }
        }
        return passengerDAO;
    }

    public static TicketDAO getTicketDAO() {
        if (ticketDAO == null) {
            synchronized (ApplicationContext.class) {
                if (ticketDAO == null) {
                    ticketDAO = new TicketDAOImpl(getEntityManager());
                }
            }
        }
        return ticketDAO;
    }

    public static TimetableDAO getTimetableDAO() {
        if (timetableDAO == null) {
            synchronized (ApplicationContext.class) {
                if (timetableDAO == null) {
                    timetableDAO = new TimetableDAOImpl(getEntityManager());
                }
            }
        }
        return timetableDAO;
    }

    public static CommonService getCommonService() {
        if (commonService == null) {
            synchronized (ApplicationContext.class) {
                if (commonService == null) {
                    commonService = new CommonServiceImpl(getStationDAO(), getTrainDAO(), getPassengerDAO());
                }
            }
        }
        return commonService;
    }

    public static AdministratorService getAdministratorService() {
        if (administratorService == null) {
            synchronized (ApplicationContext.class) {
                if (administratorService == null) {
                    administratorService = new AdministratorServiceImpl(getDaoTransactionManager(), getStationDAO(), getTrainDAO(), getPassengerDAO(), getTimetableDAO());
                }
            }
        }
        return administratorService;
    }

    public static PassengerService getPassengerService() {
        if (passengerService == null) {
            synchronized (ApplicationContext.class) {
                if (passengerService == null) {
                    passengerService = new PassengerServiceImpl(getDaoTransactionManager(), getStationDAO(), getTrainDAO(), getPassengerDAO(), getTicketDAO());
                }
            }
        }
        return passengerService;
    }

    public static TrainModel getTrainModel() {
        if (trainModel == null) {
            synchronized (ApplicationContext.class) {
                if (trainModel == null) {
                    trainModel = new TrainModel(getAdministratorService(), getPassengerService(), getCommonService());
                }
            }
        }
        return trainModel;
    }

    public static StationModel getStationModel() {
        if (stationModel == null) {
            synchronized (ApplicationContext.class) {
                if (stationModel == null) {
                    stationModel = new StationModel(getAdministratorService(), getCommonService());
                }
            }
        }
        return stationModel;
    }

    public static PassengerModel getPassengerModel() {
        if (passengerModel == null)  {
            synchronized (ApplicationContext.class) {
                if (passengerModel == null)  {
                    passengerModel = new PassengerModel(getCommonService(), getPassengerService());
                }
            }
        }
        return passengerModel;
    }
}
