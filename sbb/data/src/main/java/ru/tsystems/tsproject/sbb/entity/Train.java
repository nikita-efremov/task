package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.TreeSet;

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
            timetables = new TreeSet<Timetable>();
		}
        return timetables;
    }

    public void setTimetables(TreeSet<Timetable> timetables) {
        this.timetables = timetables;
    }

    public void addTimetable(Timetable timetable) {
        if (timetables == null) {
            timetables = new TreeSet<Timetable>();
        }
        timetables.add(timetable);
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public boolean equals(Object o) {
        if (super.equals(o)) {
            return Boolean.TRUE;
        }
        if (o == null) {
            return Boolean.FALSE;
        }
        if (this.getClass() != o.getClass()) {
            return Boolean.FALSE;
        } else {
            Train otherTrain = (Train)o;
            return ((otherTrain.getId() == this.getId())
                    && (otherTrain.getNumber().equals(this.getNumber()))
                    && (otherTrain.getTotalSeats() == this.getTotalSeats()));
        }
    }

    @Override
    public String toString() {
        StringBuilder timetableString = new StringBuilder();
        if (timetables != null) {
            for (Timetable timetable: timetables) {
                timetableString.append(timetable.toString()).append(",");
            }
        }
        return "[Train: " +
                "id=" + id + ", " +
                "number=" + number + "," +
                "seats=" + seats + "," +
                "totalSeats=" + totalSeats +
                "timetables=" + timetableString.toString()
                + "]";
    }
}
