package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public interface TimetableDAO {

    public void addTimetable(Timetable Timetable) throws DAOException;
    public Timetable getTimetableById(int timetableID) throws DAOException;
    public void updateTimetable(Timetable timetable) throws DAOException;
    public void deleteTimetable(int timetableID) throws DAOException;
    public Collection getTimetableByStation(int stationID) throws DAOException;
    public Collection getTrainsByStationsAndDate(int stationStartID, int stationEndID, Date dateStart, Date end) throws DAOException;
}
