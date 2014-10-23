package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;
import java.util.List;

/**
 * DAO-class for entity Timetable. Extends CommonDAO interface with wildcard class = Timetable
 */
public interface TimetableDAO extends CommonDAO <Timetable> {

    /**
     * Get operation: timetables wil be found by specified station primary key - database ID
     * @param stationID
     * Station primary key - database ID
     * @return Collection
     * Collection of stations, which were found by specified station primary key - database ID
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public Collection getTimetableByStation(int stationID) throws DAOException;

    /**
     * Get operation: timetables will be found by train primary key - database ID
     * @param trainID
     * Train primary key
     * @return List
     * Collection of timetables which were found by train primary key - database ID
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public Collection getTimetableByTrain(int trainID) throws DAOException;
}
