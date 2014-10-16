package ru.tsystems.tsproject.sbb.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 16.10.14
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class TicketDTO implements Serializable {

    private int id;
    private long ticketNumber;
    private PassengerDTO passengerDTO;
    private TrainDTO trainDTO;

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

    public PassengerDTO getPassengerDTO() {
        return passengerDTO;
    }

    public void setPassengerDTO(PassengerDTO passengerDTO) {
        this.passengerDTO = passengerDTO;
    }

    public TrainDTO getTrainDTO() {
        return trainDTO;
    }

    public void setTrainDTO(TrainDTO trainDTO) {
        this.trainDTO = trainDTO;
    }
}
