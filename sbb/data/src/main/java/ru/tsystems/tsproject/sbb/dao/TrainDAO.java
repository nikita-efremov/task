package ru.tsystems.tsproject.sbb.dao;

import ru.tsystems.tsproject.sbb.entity.Train;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public interface TrainDAO {

    public void addTrain(Train train);
    public Train getTrainByID(int trainID);
    public void decreaseSeatAmount(int trainID);
    public Collection getAllTrains();
}
