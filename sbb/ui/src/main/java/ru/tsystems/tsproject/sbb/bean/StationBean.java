package ru.tsystems.tsproject.sbb.bean;

import ru.tsystems.tsproject.sbb.entity.Timetable;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.TreeSet;

/**
 * View bean class which contents information about the station. It is using for representing data on view layer
 * @author  Nikita Efremov
 * @since   1.0
 */
public class StationBean extends BaseBean {

    private int id = 0;

    @Pattern(regexp = "[A-Za-z]+", message="Station name must contain only english letters, one or more")
    private String name = "";

    private Set<TimetableBean> timetables = new TreeSet<TimetableBean>();

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

    public Set<TimetableBean> getTimetables() {
        return timetables;
    }

    public void setTimetables(Set<TimetableBean> timetables) {
        this.timetables = timetables;
    }

    @Override
    public String toString() {
        StringBuilder timetable = new StringBuilder();
        if (timetables != null) {
            for (TimetableBean timetableBean: timetables) {
                timetable.append(timetableBean.toString()).append(",");
            }
        }
        return "[StationBean: " +
                "id=" + id + ", " +
                "name=" + name + "," +
                "timetables=" + timetable.toString()
                + "]";
    }
}
