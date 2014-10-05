package ru.tsystems.tsproject.sbb.bean;

import ru.tsystems.tsproject.sbb.entity.Timetable;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Bean class which contents information about the station
 * @author  Nikita Efremov
 * @since   1.0
 */
public class StationBean extends BaseBean {

    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Za-z]+", message="Station name must contain only english letters, one or more")
    private String name;

    private Set<Timetable> timetables;

    public StationBean() {
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

    public void setTimetables(Set<Timetable> timetables) {
        this.timetables = timetables;
    }
}
