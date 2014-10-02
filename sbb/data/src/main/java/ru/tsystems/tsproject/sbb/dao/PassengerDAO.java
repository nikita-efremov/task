package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Passenger;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public interface PassengerDAO {

    public void addPassenger(Passenger passenger);
    public void updatePassenger(int passengerID, Passenger passenger);
    public Passenger getPassengerById(int passengerID);
}
