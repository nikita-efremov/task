package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;

public interface PassengerDAO extends CommonDAO <Passenger> {

	public Collection getPassengersByTrain(int trainID) throws DAOException;
    public Passenger getPassengerByDocumentNumber(String docNumber) throws DAOException;
}
