package ru.tsystems.tsproject.sbb.entity;


import java.util.*;

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

    @Column(name = "doc_number")
    private String docNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "passenger")
    private Set<Ticket> tickets;

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

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Set<Ticket> getTickets() {
		if (tickets == null) {
			return new HashSet<Ticket>();
		} else {
			return tickets;
		}
    }

    public void setTickets(HashSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        if (tickets == null) {
            tickets = new TreeSet<Ticket>();
        }
        tickets.add(ticket);
    }

    public boolean equals(Object o) {
        if (super.equals(o)) {
            return Boolean.TRUE;
        }
        if (o == null) {
            return Boolean.FALSE;
        }
        if (this.getClass() != o.getClass()) {
            return Boolean.FALSE;
        } else {
            Passenger otherPassenger = (Passenger)o;
            return ((otherPassenger.getId() == this.getId())
                    && (otherPassenger.getDocNumber().equals(this.getDocNumber()))
                    && (otherPassenger.getLastName().equals(this.getLastName()))
                    && (otherPassenger.getFirstName().equals(this.getFirstName()))
                    && (otherPassenger.getBirthDate().equals(this.getBirthDate())));
        }
    }
}
