package ru.tsystems.tsproject.sbb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 16.10.14
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class PassengerDTO implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String docNumber;
    private Set<TicketDTO> tickets;

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

    public Set<TicketDTO> getTickets() {
        if (tickets == null) {
            return new TreeSet<TicketDTO>();
        } else {
            return tickets;
        }
    }

    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
