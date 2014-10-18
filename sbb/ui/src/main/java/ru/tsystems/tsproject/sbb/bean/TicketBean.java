package ru.tsystems.tsproject.sbb.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 17.10.14
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class TicketBean extends BaseBean {

    private int id;

    @Min(value = 1, message = "Ticket number must not be smaller than 1")
    @Max(value = 999999999999999L, message = "Ticket number must not be bigger than " + 999999999999999L)
    private long ticketNumber;

    @Pattern(regexp = "[A-Za-z0-9]{10}", message="Document number must contain 10 symbols: only english letters and digits")
    private String passengerDocNumber;

    @Pattern(regexp = "[A-Za-z0-9]+", message = "Train number name must contain only english letters and digits, one or more")
    private String trainNumber;

    public TicketBean() {
        passengerDocNumber = "";
        trainNumber = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getPassengerDocNumber() {
        return passengerDocNumber;
    }

    public void setPassengerDocNumber(String passengerDocNumber) {
        this.passengerDocNumber = passengerDocNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
}
