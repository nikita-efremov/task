package ru.tsystems.tsproject.sbb.service;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface AdministratorService {

    public void addStation(Station station);
    public void addTrain(Collection<Timetable> timetables);
    public Collection<Passenger> getPassengersByTrain(Train train);
    public Collection<Train> getAllTrains();
}
