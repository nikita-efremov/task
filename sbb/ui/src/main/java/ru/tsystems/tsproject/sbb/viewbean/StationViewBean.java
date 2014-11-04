package ru.tsystems.tsproject.sbb.viewbean;

import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.TreeSet;

/**
 * View bean class which contents information about the station. It is using for representing data on view layer
 * @author  Nikita Efremov
 * @since   1.0
 */
public class StationViewBean extends BaseViewBean {

    private int id = 0;

    @Pattern(regexp = "[A-Za-z]+", message="Station name must contain only english letters, one or more")
    private String name = "";

    private Set<TimetableViewBean> timetables = new TreeSet<TimetableViewBean>();

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

    public Set<TimetableViewBean> getTimetables() {
        return timetables;
    }

    public void setTimetables(Set<TimetableViewBean> timetables) {
        this.timetables = timetables;
    }

    @Override
    public String toString() {
        StringBuilder timetable = new StringBuilder();
        if (timetables != null) {
            for (TimetableViewBean timetableBean: timetables) {
                timetable.append(timetableBean.toString()).append(",");
            }
        }
        return "[StationViewBean: " +
                "id=" + id + ", " +
                "name=" + name + "," +
                "timetables=" + timetable.toString()
                + "]";
    }
}
