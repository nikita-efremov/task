package ru.tsystems.tsproject.sbb.dao.api;
import ru.tsystems.tsproject.sbb.dao.DAOException;
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
public interface TrainDAO extends CommonDAO <Train> {

    public Train getTrainByNumber(String trainNumber) throws DAOException;
    public Collection getTrainsByStationsAndDate(int stationStartID, int stationEndID, Date dateStart, Date end) throws DAOException;
    public void decreaseSeatAmount(int trainID) throws DAOException;
}
