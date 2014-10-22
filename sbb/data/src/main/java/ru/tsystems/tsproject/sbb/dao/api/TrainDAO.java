package ru.tsystems.tsproject.sbb.dao.api;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.entity.Train;

import java.util.Collection;
import java.util.Date;

public interface TrainDAO extends CommonDAO <Train> {

    public Train getTrainByNumber(String trainNumber) throws DAOException;
    public Collection getTrainsByStationsAndDate(int stationStartID, int stationEndID, Date dateStart, Date end) throws DAOException;
    public void decreaseSeatAmount(int trainID) throws DAOException;
}
