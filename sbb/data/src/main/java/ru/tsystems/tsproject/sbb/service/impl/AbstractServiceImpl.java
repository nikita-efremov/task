package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.*;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 15.10.14
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractServiceImpl {
    private StationDAO stationDAO;
    private TrainDAO trainDAO;
    private PassengerDAO passengerDAO;
    private TimetableDAO timetableDAO;
    private TicketDAO ticketDAO;

    public AbstractServiceImpl(StationDAO stationDAO,
                               TrainDAO trainDAO,
                               PassengerDAO passengerDAO,
                               TimetableDAO timetableDAO,
                               TicketDAO ticketDAO) {
        this.stationDAO = stationDAO;
        this.trainDAO = trainDAO;
        this.passengerDAO = passengerDAO;
        this.timetableDAO = timetableDAO;
        this.ticketDAO = ticketDAO;
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

    public TimetableDAO getTimetableDAO() {
        return timetableDAO;
    }

    public void setTimetableDAO(TimetableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }

    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }
}
