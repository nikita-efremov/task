package ru.tsystems.tsproject.sbb.bean;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 17.10.14
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class TimetableBean extends BaseBean implements Comparable<TimetableBean> {

    private int id;

    @Pattern(regexp = "[A-Za-z0-9]+", message = "Train number name must contain only english letters and digits, one or more")
    private String trainNumber;

    @Pattern(regexp = "[A-Za-z]+", message = "Station name must contain only english letters, one or more")
    private String stationName;

    @Future(message = "Train departure date must be in future")
    @NotNull(message = "Date format: dd-MM-yyyyTHH:mm")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(TimetableBean other) {
       if (this.date.after(other.date)) {
           return 1;
       } else if (this.date.equals(other.date)){
           return 0;
       } else {
           return -1;
       }
    }

}
