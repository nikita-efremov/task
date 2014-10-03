package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "train")
public class Train {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "seats")
    private int seats;

    @Column(name = "total_seats")
    private int total_seats;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
    private Set<Timetable> timetables;

    public Train() {
        id = 0;
        number = "";
        seats = 0;
        total_seats = 0;
        timetables = new TreeSet<Timetable>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Set<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(TreeSet<Timetable> timetables) {
        this.timetables = timetables;
    }

    public void addTimetable(Timetable timetable) {
        timetables.add(timetable);
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }
}
