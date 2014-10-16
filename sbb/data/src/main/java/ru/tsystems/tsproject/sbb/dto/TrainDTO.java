package ru.tsystems.tsproject.sbb.dto;

import ru.tsystems.tsproject.sbb.entity.Timetable;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 16.10.14
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class TrainDTO implements Serializable {

    private int id;
    private String number;
    private int seats;
    private int totalSeats;
    private Set<TimetableDTO> timetables;

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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Set<TimetableDTO> getTimetables() {
        if (timetables == null) {
            return new TreeSet<TimetableDTO>();
        } else {
            return timetables;
        }
    }

    public void setTimetables(Set<TimetableDTO> timetables) {
        this.timetables = timetables;
    }
}
