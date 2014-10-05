package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
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

    public Station() {
        id = 0;
        name = "";
        timetables = new TreeSet<Timetable>();
    }

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
        return timetables;
    }

    public void setTimetables(TreeSet<Timetable> timetables) {
        this.timetables = timetables;
    }

    public void addTimetable(Timetable timetable) {
        timetables.add(timetable);
    }
}
