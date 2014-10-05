package ru.tsystems.tsproject.sbb.entity;


import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @Pattern(regexp = "[A-Za-z]+", message="First name must contain only english letters, one or more")
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp = "[A-Za-z]+", message="Last name must contain only english letters, one or more")
    private String lastName;

    @Column(name = "birthdate")
    private Date birthDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "passenger")
    private Set<Ticket> tickets;

    public Passenger() {
        id = 0;
        firstName = "";
        lastName = "";
        birthDate = new Date();
        tickets = new HashSet<Ticket>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(HashSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
