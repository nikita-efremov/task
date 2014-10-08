package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.exception.DAOException;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public interface PassengerDAO {

    public void addPassenger(Passenger passenger) throws DAOException;
    public Passenger getPassengerById(int passengerID) throws DAOException;
    public void updatePassenger(Passenger passenger) throws DAOException;
    public void deletePassenger(Passenger passenger) throws DAOException;
}
