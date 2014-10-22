package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket implements Comparable<Ticket> {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ticket_number")
    private long ticketNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Train train;

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

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public int compareTo(Ticket other) {
        if (this.id > other.id) {
            return 1;
        } else if (this.id == other.id) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String trainString = "";
        if (train != null) {
            trainString = train.getNumber();
        }
        String passengerString = "";
        if (passenger != null) {
            passengerString = passenger.getDocNumber();
        }

        return "[Ticket: " +
                "id=" + id + "," +
                "ticketNumber=" + ticketNumber+ "," +
                "trainNumber=" + trainString + "," +
                "docNumber=" + passengerString + "," +
                "]";
    }
}
