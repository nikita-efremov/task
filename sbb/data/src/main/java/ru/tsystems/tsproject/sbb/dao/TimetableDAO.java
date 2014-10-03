package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;

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

    public void addTimetable(Timetable Timetable);
    public Timetable getTimetableById(int timetableID);
    public void updateTimetable(Timetable timetable);
    public void deleteTimetable(Timetable timetable);
    public Collection getTimetableByStation(Station station);
    public Collection getTrainsByStationsAndDate(Station stationStart, Station stationEnd,Date dateStart, Date end);
}
