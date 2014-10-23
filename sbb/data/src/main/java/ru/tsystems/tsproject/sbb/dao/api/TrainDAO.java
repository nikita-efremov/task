package ru.tsystems.tsproject.sbb.dao.api;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.entity.Train;

import java.util.Collection;
import java.util.Date;

/**
 * DAO-class for entity Train. Extends CommonDAO interface with wildcard class = Train
 */
public interface TrainDAO extends CommonDAO <Train> {

    /**
     * Get operation: train will be got by its unique field - trainNumber
     * @param trainNumber
     * Unique field - number of train
     * @return Train
     * Train object which was found by its unique field - trainNumber
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public Train getTrainByNumber(String trainNumber) throws DAOException;

    /**
     * Get operation: trains will be found by departure station primary key or arrival station primary key,
     * and train departure dates must be between specified dateStart and dateEnd
     * @param stationStartID
     * Departure station primary key - database ID
     * @param stationEndID
     * Arrival station primary key - database ID
     * @param dateStart
     * Minimal departure date for train
     * @param dateEnd
     * Maximal departure date for train
     * @return Collection
     * Found trains
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public Collection getTrainsByStationsAndDate(int stationStartID, int stationEndID, Date dateStart, Date dateEnd) throws DAOException;

    /**
     * Update operation: train will be found by its primary key, then its seatAmount will be decreased by 1
     * and then database value will be updated
     * @param trainID
     * Train primary key - database ID
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public void decreaseSeatAmount(int trainID) throws DAOException;
}
