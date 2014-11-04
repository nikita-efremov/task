package ru.tsystems.tsproject.sbb.controller.helpers;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.tsproject.sbb.viewbean.PassengerViewBean;
import ru.tsystems.tsproject.sbb.viewbean.TicketViewBean;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Part of a controller which launches appropriate service method, related to passenger,
 * and makes mapping from view bean class to service bean class and conversely
 * @author  Nikita Efremov
 * @since   1.0
 */
@Component
public class PassengerControllersHelper {

    private static final Logger log = Logger.getLogger(PassengerControllersHelper.class);

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private AdministratorService administratorService;

    /**
     * Method for adding new passenger with last name, first name, document number and birth date, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  passengerBean
     *         Passenger instance with default id value and specified last name, first name, document number and birth date
     *
     * @return PassengerViewBean
     *         result of processing
     */
    public PassengerViewBean addPassenger(PassengerViewBean passengerBean) {
        try {
            String passwordHash = DigestUtils.md5Hex(passengerBean.getPassword());

            Passenger passenger = new Passenger();
            passenger.setLastName(passengerBean.getLastName());
            passenger.setFirstName(passengerBean.getFirstName());
            passenger.setDocNumber(passengerBean.getDocNumber());
            passenger.setBirthDate(passengerBean.getBirthDate());
            passenger.setPassword(passwordHash);

            passenger = passengerService.addPassenger(passenger);

            passengerBean.setId(passenger.getId());
            passengerBean.setLastName(passenger.getLastName());
            passengerBean.setFirstName(passenger.getFirstName());
            passengerBean.setDocNumber(passenger.getDocNumber());
            passengerBean.setBirthDate(passenger.getBirthDate());
            passengerBean.setPassword(passenger.getPassword());

        } catch (PassengerAlreadyExistsException e) {
            passengerBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            passengerBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            passengerBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return passengerBean;
    }

    /**
     * Searches passenger by document number, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  passengerBean
     *         Passenger instance with default id value and specified last name, first name, document number and birth date
     *
     * @return PassengerViewBean
     *         result of processing
     */
    public PassengerViewBean getPassenger(PassengerViewBean passengerBean) {
        try {
            Passenger passenger = passengerService.findPassenger(passengerBean.getDocNumber());

            passengerBean.setId(passenger.getId());
            passengerBean.setLastName(passenger.getLastName());
            passengerBean.setFirstName(passenger.getFirstName());
            passengerBean.setDocNumber(passenger.getDocNumber());
            passengerBean.setBirthDate(passenger.getBirthDate());
        } catch (PassengerNotExistsException e) {
            passengerBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            passengerBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            passengerBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return passengerBean;
    }

    /**
     * Searches passenger by document number, specified in param. Then, it returns collection of all passenger tickets
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  passengerBean
     *         Passenger instance with default id value and specified last name, first name, document number and birth date
     *
     * @return Collection<TicketViewBean>
     *         collection of found tickets
     */
    public Collection<TicketViewBean> getPassengerTickets(PassengerViewBean passengerBean) {
        Collection<TicketViewBean> ticketBeans = new LinkedList<TicketViewBean>();
        try {
            Passenger passenger = passengerService.findPassenger(passengerBean.getDocNumber());
            Collection<Ticket> tickets = passenger.getTickets();
            for (Ticket ticket: tickets) {
                TicketViewBean ticketBean = new TicketViewBean();
                ticketBean.setTicketNumber(ticket.getTicketNumber());
                ticketBean.setTrainNumber(ticket.getTrain().getNumber());
                ticketBeans.add(ticketBean);
            }
        } catch (PassengerNotExistsException e) {
            passengerBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            passengerBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            passengerBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return ticketBeans;
    }

    /**
     * Searches passenger by document number, searches train by train number specified in param,
     * and then purchases ticket for passenger only if train has free seats,
     * passengers with same name and birth date is not registered on this train
     * and departure time bigger than current time at least on 10 minutes
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  ticketBean
     *         Passenger instance with default id value and specified last name, first name, document number and birth date
     *
     * @return TicketViewBean
     *         result of processing
     */
    public TicketViewBean purchaseTicket(TicketViewBean ticketBean) {
        try {
            Ticket ticket = passengerService.purchaseTicket(ticketBean.getTrainNumber(), ticketBean.getPassengerDocNumber());

            ticketBean.setPassengerDocNumber(ticket.getPassenger().getDocNumber());
            ticketBean.setTrainNumber(ticket.getTrain().getNumber());
            ticketBean.setTicketNumber(ticket.getTicketNumber());
            ticketBean.setId(ticket.getId());
        } catch (PassengerAlreadyRegisteredOnTrainException e) {
            ticketBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (PassengerNotExistsException e) {
            ticketBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (TrainAlreadyFullException e) {
            ticketBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (TrainAlreadyDepartedException e) {
            ticketBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (TrainNotExistsException e) {
            ticketBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e.getMessage() + " - " + e);
        } catch (DAOException e) {
            ticketBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            ticketBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return ticketBean;
    }

    /**
     * Gets collection of all passengers, which exist in system
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @return Collection<PassengerViewBean>
     *         collection of passengers
     */
    public Collection<PassengerViewBean> getAllPassengers() {
        Collection<PassengerViewBean> passengerBeans = new ArrayList<PassengerViewBean>();
        try {
            Collection<Passenger> passengers = administratorService.getAllPassengers();
            for (Passenger passenger: passengers) {
                PassengerViewBean passengerBean = new PassengerViewBean();
                passengerBean.setId(passenger.getId());
                passengerBean.setDocNumber(passenger.getDocNumber());
                passengerBean.setFirstName(passenger.getFirstName());
                passengerBean.setLastName(passenger.getLastName());
                passengerBean.setBirthDate(passenger.getBirthDate());
                passengerBeans.add(passengerBean);
            }
        } catch (DAOException e) {
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        }
        return passengerBeans;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
}
