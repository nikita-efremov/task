package ru.tsystems.tsproject.sbb;

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

public class ApplicationContext {

    private static EntityManager entityManager;

    private static TrainDAO trainDAO;
    private static StationDAO stationDAO;
    private static PassengerDAO passengerDAO;
    private static TicketDAO ticketDAO;
    private static TimetableDAO timetableDAO;

    private static CommonService commonService;
    private static AdministratorService administratorService;
    private static PassengerService passengerService;

    private static TrainModel trainModel;
    private static StationModel stationModel;
    private static PassengerModel passengerModel;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("jpa-hibernate").createEntityManager();
        }
        return entityManager;
    }

    public static TrainDAO getTrainDAO() {
        if (trainDAO == null) {
            trainDAO = new TrainDAOImpl(getEntityManager());
        }
        return trainDAO;
    }

    public static StationDAO getStationDAO() {
        if (stationDAO == null) {
            stationDAO = new StationDAOImpl(getEntityManager());
        }
        return stationDAO;
    }

    public static PassengerDAO getPassengerDAO() {
        if (passengerDAO == null) {
            passengerDAO = new PassengerDAOImpl(getEntityManager());
        }
        return passengerDAO;
    }

    public static TicketDAO getTicketDAO() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAOImpl(getEntityManager());
        }
        return ticketDAO;
    }

    public static TimetableDAO getTimetableDAO() {
        if (timetableDAO == null) {
            timetableDAO = new TimetableDAOImpl(getEntityManager());
        }
        return timetableDAO;
    }

    public static CommonService getCommonService() {
        if (commonService == null) {
            commonService = new CommonServiceImpl(getStationDAO(), getTrainDAO(), getPassengerDAO());
        }
        return commonService;
    }

    public static AdministratorService getAdministratorService() {
        if (administratorService == null) {
            administratorService = new AdministratorServiceImpl(getStationDAO(), getTrainDAO(), getPassengerDAO(), getTimetableDAO());
        }
        return administratorService;
    }

    public static PassengerService getPassengerService() {
        if (passengerService == null) {
            passengerService = new PassengerServiceImpl(getStationDAO(), getTrainDAO(), getPassengerDAO(), getTicketDAO());
        }
        return passengerService;
    }

    public static TrainModel getTrainModel() {
        if (trainModel == null) {
            trainModel = new TrainModel(getAdministratorService(), getPassengerService(), getCommonService());
        }
        return trainModel;
    }

    public static StationModel getStationModel() {
        if (stationModel == null) {
            stationModel = new StationModel(getAdministratorService(), getCommonService());
        }
        return stationModel;
    }

    public static PassengerModel getPassengerModel() {
        if (passengerModel == null)  {
            passengerModel = new PassengerModel(getCommonService(), getPassengerService());
        }
        return passengerModel;
    }
}
