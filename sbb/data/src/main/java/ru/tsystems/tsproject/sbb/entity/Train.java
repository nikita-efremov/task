package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    @Pattern(regexp = "[A-Za-z0-9]+", message="Train number name must contain only english letters and digits, one or more")
    private String number;

    @Column(name = "seats")
    private int seats;

    @Column(name = "total_seats")
    private int totalSeats;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
    private Set<Timetable> timetables;

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
		if (timetables == null) {
			return new TreeSet<Timetable>();
		} else {
			return timetables;
		}
    }

    public void setTimetables(TreeSet<Timetable> timetables) {
        this.timetables = timetables;
    }

    public void addTimetable(Timetable timetable) {
        timetables.add(timetable);
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}
