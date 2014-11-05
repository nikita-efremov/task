package ru.tsystems.tsproject.sbb.viewbean;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * View bean class which contents information about the complex train search. It is using for representing data on view layer
 * @author  Nikita Efremov
 * @since   2.0
 */
public class ComplexTrainSearchViewBean extends BaseViewBean {

    public static final String DEFAULT_NAME = "complexTrainSearchBean";

    @Pattern(regexp = "[A-Za-z]+", message = "Station name must contain only english letters, one or more")
    private String stationStartName = "";

    @Future(message = "Train departure date must be in future")
    @NotNull(message = "Date format: dd-MM-yyyy HH:mm")
    private Date startDate;

    @Pattern(regexp = "[A-Za-z]+", message = "Station name must contain only english letters, one or more")
    private String stationEndName = "";

    @Future(message = "Train departure date must be in future")
    @NotNull(message = "Date format: dd-MM-yyyy HH:mm")
    private Date endDate;

    public String getStationStartName() {
        return stationStartName;
    }

    public void setStationStartName(String stationStartName) {
        this.stationStartName = stationStartName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStationEndName() {
        return stationEndName;
    }

    public void setStationEndName(String stationEndName) {
        this.stationEndName = stationEndName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String startDateString = "";
        if (startDate != null) {
            startDateString = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(startDate);
        }
        String endDateString = "";
        if (endDate != null) {
            endDateString = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(endDate);
        }
        return "[ComplexTrainSearchViewBean: " +
                "stationStartName=" + stationStartName + "," +
                "stationEndName=" + stationEndName + "," +
                "startDate=" + startDateString + "," +
                "endDate=" + endDateString
                + "]";
    }
}
