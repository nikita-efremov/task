package ru.tsystems.tsproject.sbb.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Train train;

    public Ticket() {
        id = 0;
        passenger = new Passenger();
        train = new Train();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
