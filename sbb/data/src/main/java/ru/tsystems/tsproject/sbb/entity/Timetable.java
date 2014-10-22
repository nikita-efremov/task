package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "timetable")
public class Timetable implements Comparable<Timetable> {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(name = "date")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Timetable other) {
        if (this.date.after(other.date)) {
            return 1;
        } else if (this.date.equals(other.date)){
            return 0;
        } else {
            return -1;
        }
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
            Timetable otherTimetable = (Timetable)o;
            return ((otherTimetable.getId() == this.getId())
                    && (otherTimetable.getStation().getId() == this.getStation().getId())
                    && (otherTimetable.getTrain().getId() == this.getTrain().getId())
                    && (otherTimetable.getDate().equals(this.getDate())));
        }
    }

    @Override
    public String toString() {
        String dateString = "";
        if (date != null) {
           dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
        }
        String trainString = "";
        if (train != null) {
            trainString = train.getNumber();
        }
        String stationString = "";
        if (station != null) {
            stationString = station.getName();
        }
        return "[Timetable: " +
                "id=" + id + "," +
                "trainNumber=" + trainString + "," +
                "stationName=" + stationString + "," +
                "depDate=" + dateString +
                "]";
    }
}
