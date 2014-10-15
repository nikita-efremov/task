package ru.tsystems.tsproject.sbb.service.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public interface PassengerService {

    public Collection<Train> findTrainsByStationsAndDate(Station stationStart, Station stationEnd, Date start, Date end);
    public Collection<Train> getTrainsByStation(Station station);
    public void purchaseTicket(Train train, Passenger passenger);
}
