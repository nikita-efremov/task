package ru.tsystems.tsproject.sbb.bean;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 17.10.14
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class TrainBean extends BaseBean {

    private int id;

    @Pattern(regexp = "[A-Za-z0-9]+", message = "Train number name must contain only english letters and digits, one or more")
    private String number;

    private String seats;

    @Min(value = 1, message = "Train seat value must not be smaller, than 1")
    @Max(value = 999, message = "Train seat value must be bigger, than 999")
    @Pattern(regexp = "[0-9]{1,3}", message = "Train seats value must contain only digits")
    private String totalSeats;

    private Set<TimetableBean> timetables;

    public TrainBean() {
        number = "";
        seats = "";
        totalSeats = "";

    }


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

    public Set<TimetableBean> getTimetables() {
        if (timetables == null) {
            return new TreeSet<TimetableBean>();
        } else {
            return timetables;
        }
    }

    public void setTimetables(Set<TimetableBean> timetables) {
        this.timetables = timetables;
    }
}
