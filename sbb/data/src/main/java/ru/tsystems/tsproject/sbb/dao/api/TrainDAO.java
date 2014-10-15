package ru.tsystems.tsproject.sbb.dao.api;
import ru.tsystems.tsproject.sbb.exception.DAOException;
import ru.tsystems.tsproject.sbb.entity.Train;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public interface TrainDAO {

    public void addTrain(Train train) throws DAOException;
    public Train getTrainByID(int trainID) throws DAOException;
    public Train getTrainByNumber(String trainNumber) throws DAOException;
    public Collection getTrainsByStationsAndDate(int stationStartID, int stationEndID, Date dateStart, Date end) throws DAOException;
    public void updateTrain(Train train) throws DAOException;
    public void deleteTrain(int trainID) throws DAOException;
    public void decreaseSeatAmount(int trainID) throws DAOException;
    public Collection getAllTrains() throws DAOException;
}
