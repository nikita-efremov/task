package ru.tsystems.tsproject.sbb.dao.api;

import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;

/**
 * DAO-class for entity Passenger. Extends CommonDAO interface with wildcard class = Passenger
 * @author  Nikita Efremov
 * @since   1.0
 */
public interface PassengerDAO extends CommonDAO <Passenger> {

    /**
     * Get operation: Passenger objects will be got by train number in tickets, which passengers may have
     * @param trainID
     * Train entity primary key
     * @return Collection
     * Collection of found passenger objects
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
	public Collection getPassengersByTrain(int trainID) throws DAOException;

    /**
     * Get operation: Passenger object will be got by its unique field documentNumber
     * @param docNumber
     * Passenger`s document number
     * @return Passenger
     * Found passenger object
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public Passenger getPassengerByDocumentNumber(String docNumber) throws DAOException;
}
