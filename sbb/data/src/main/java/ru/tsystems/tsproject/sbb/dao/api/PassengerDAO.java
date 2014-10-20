package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;
/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public interface PassengerDAO extends CommonDAO <Passenger> {

	public Collection getPassengersByTrain(int trainID) throws DAOException;
    public Collection getPassengerByDocumentNumber(String docNumber) throws DAOException;
}
