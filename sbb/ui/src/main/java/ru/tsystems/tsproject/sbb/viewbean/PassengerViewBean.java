package ru.tsystems.tsproject.sbb.viewbean;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * View bean class which contents information about passenger. It is using for representing data on view layer
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerViewBean extends BaseViewBean {

    public static final String DEFAULT_NAME = "passengerBean";

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

    private Set<TicketViewBean> tickets = new TreeSet<TicketViewBean>();

    @Pattern(regexp = "[A-Za-z0-9]{6,16}", message="Password must contain only english letters and digits, from 6 to 16 symbols")
    private String password;

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

    public Set<TicketViewBean> getTickets() {
        if (tickets == null) {
            return new TreeSet<TicketViewBean>();
        } else {
            return tickets;
        }
    }

    public void setTickets(Set<TicketViewBean> tickets) {
        this.tickets = tickets;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder ticketsString = new StringBuilder();
        if (tickets != null) {
            for (TicketViewBean ticketBean: tickets) {
                ticketsString.append(ticketBean.toString()).append(",");
            }
        }
        return "[PassengerViewBean: " +
                "id=" + id + ", " +
                "docNumber=" + docNumber + "," +
                "lastName=" + lastName + "," +
                "firstName=" + firstName + "," +
                "birthDate=" + birthDate + "," +
                "tickets=" + ticketsString.toString()
                + "]";
    }
}
