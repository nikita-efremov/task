package ru.tsystems.tsproject.sbb.bean;

import ru.tsystems.tsproject.sbb.entity.Ticket;

import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class PassengerBean extends BaseBean {

    private int id;

    @Pattern(regexp = "[A-Za-z]+", message="First name must contain only english letters, one or more")
    private String firstName = "";

    @Pattern(regexp = "[A-Za-z]+", message="Last name must contain only english letters, one or more")
    private String lastName = "";

    @Past(message = "Passenger birth date must be in the past")
    @NotNull(message = "Date format: dd-MM-yyyy")
    private Date birthDate;

    @Pattern(regexp = "[A-Za-z0-9]{10}", message="Document number must contain 10 symbols: only english letters and digits")
    private String docNumber = "";

    private Set<TicketBean> tickets = new TreeSet<TicketBean>();

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

    public Set<TicketBean> getTickets() {
        if (tickets == null) {
            return new TreeSet<TicketBean>();
        } else {
            return tickets;
        }
    }

    public void setTickets(Set<TicketBean> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        StringBuilder ticketsString = new StringBuilder();
        if (tickets != null) {
            for (TicketBean ticketBean: tickets) {
                ticketsString.append(ticketBean.toString()).append(",");
            }
        }
        return "[PassengerBean: " +
                "id=" + id + ", " +
                "docNumber=" + docNumber + "," +
                "lastName=" + lastName + "," +
                "firstName=" + firstName + "," +
                "birthDate=" + birthDate + "," +
                "tickets=" + ticketsString.toString()
                + "]";
    }
}
