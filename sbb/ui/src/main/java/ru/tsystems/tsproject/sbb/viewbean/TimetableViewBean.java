package ru.tsystems.tsproject.sbb.viewbean;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * View bean class which contents information about the timetable. It is using for representing data on view layer
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TimetableViewBean extends BaseViewBean implements Comparable<TimetableViewBean> {

    private int id;

    @Pattern(regexp = "[A-Za-z0-9]+", message = "Train number name must contain only english letters and digits, one or more")
    private String trainNumber = "";

    @Pattern(regexp = "[A-Za-z]+", message = "Station name must contain only english letters, one or more")
    private String stationName = "";

    @Future(message = "Train departure date must be in future")
    @NotNull(message = "Date format: dd-MM-yyyy HH:mm")
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
    public int compareTo(TimetableViewBean other) {
       if (this.date.after(other.date)) {
           return 1;
       } else {
           return -1;
       }
    }

    @Override
    public String toString() {
        String dateString = "";
        if (date != null) {
            dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
        }
        return "[Timetable: " +
                "id=" + id + "," +
                "trainNumber=" + trainNumber + "," +
                "stationName=" + stationName + "," +
                "depDate=" + dateString +
                "]";
    }
}
