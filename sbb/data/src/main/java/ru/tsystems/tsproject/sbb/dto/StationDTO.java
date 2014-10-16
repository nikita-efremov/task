package ru.tsystems.tsproject.sbb.dto;

import ru.tsystems.tsproject.sbb.entity.Timetable;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 16.10.14
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class StationDTO implements Serializable {

    private int id;
    private String name;
    private Set<TimetableDTO> timetables;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
