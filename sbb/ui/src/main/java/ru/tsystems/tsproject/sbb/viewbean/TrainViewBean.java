package ru.tsystems.tsproject.sbb.viewbean;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * View bean class which contents information about the train. It is using for representing data on view layer
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TrainViewBean extends BaseViewBean {

    public static final String DEFAULT_NAME = "trainBean";

    private int id;

    @Pattern(regexp = "[A-Za-z0-9]+", message = "Train number name must contain only english letters and digits, one or more")
    private String number = "";

    private String seats = "";

    @Min(value = 1, message = "Train seat value must not be smaller, than 1")
    @Max(value = 999, message = "Train seat value must be bigger, than 999")
    @Pattern(regexp = "[0-9]{1,3}", message = "Train seats value must contain only digits")
    private String totalSeats = "";

    private Set<TimetableViewBean> timetables = new TreeSet<TimetableViewBean>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(String totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Set<TimetableViewBean> getTimetables() {
        if (timetables == null) {
            return new TreeSet<TimetableViewBean>();
        } else {
            return timetables;
        }
    }

    public void setTimetables(Set<TimetableViewBean> timetables) {
        this.timetables = timetables;
    }

    @Override
    public String toString() {
        StringBuilder timetable = new StringBuilder();
        if (timetables != null) {
            for (TimetableViewBean timetableBean: timetables) {
                timetable.append(timetableBean.toString()).append(",");
            }
        }
        return "[TrainViewBean: " +
                "id=" + id + ", " +
                "number=" + number + "," +
                "seats=" + seats + "," +
                "totalSeats=" + totalSeats +
                "timetables=" + timetable.toString()
                + "]";
    }
}
