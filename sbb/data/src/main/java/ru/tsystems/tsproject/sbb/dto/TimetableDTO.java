package ru.tsystems.tsproject.sbb.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 16.10.14
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class TimetableDTO implements Serializable {

    private int id;
    private TrainDTO trainDTO;
    private StationDTO stationDTO;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrainDTO getTrainDTO() {
        return trainDTO;
    }

    public void setTrainDTO(TrainDTO trainDTO) {
        this.trainDTO = trainDTO;
    }

    public StationDTO getStationDTO() {
        return stationDTO;
    }

    public void setStationDTO(StationDTO stationDTO) {
        this.stationDTO = stationDTO;
    }
}
