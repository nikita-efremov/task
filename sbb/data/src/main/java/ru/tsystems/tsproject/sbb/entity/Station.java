package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.constraints.Pattern;

/**
 * Class represents JPA entity - Station. It is mapped on table station
 * @author  Nikita Efremov
 * @since   1.0
 */

@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Za-z]+", message="Station name must contain only english letters, one or more")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station")
    private Set<Timetable> timetables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            Station otherStation = (Station)o;
            return ((otherStation.getId() == this.getId())
                    && (otherStation.getName().equals(this.getName()))
                    && (otherStation.getTimetables().equals(this.getTimetables())));
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
        return "[Station: " +
                "id=" + id + ", " +
                "name=" + name + "," +
                "timetables=" + timetableString.toString()
                + "]";
    }
}
