package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;
import java.util.List;

public interface TimetableDAO extends CommonDAO <Timetable> {

    public Collection getTimetableByStation(int stationID) throws DAOException;
    public List getTimetableByTrain(int trainID) throws DAOException;
}
