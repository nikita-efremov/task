package ru.tsystems.tsproject.sbb.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.TicketBean;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

/**
 *
 * Class implements model behaviour of mvc pattern of object passenger
 * @author  Nikita Efremov
 * @since   1.0
 */

public class PassengerModel {

    private static final Logger log = Logger.getLogger(PassengerModel.class);

    private CommonService commonService;
    private PassengerService passengerService;

    public PassengerModel(CommonService commonService, PassengerService passengerService) {
        this.commonService = commonService;
        this.passengerService = passengerService;
    }

    /**
     * Adds new passenger with last name, first name, document number and birth date, specified in param.
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  passengerBean
     *         Passenger instance with default id value and specified last name, first name, document number and birth date
     *
     * @return result of processing
     */
    public PassengerBean addPassenger(PassengerBean passengerBean) {
        try {
            Passenger passenger = new Passenger();
            passenger.setLastName(passengerBean.getLastName());
            passenger.setFirstName(passengerBean.getFirstName());
            passenger.setDocNumber(passengerBean.getDocNumber());
            passenger.setBirthDate(passengerBean.getBirthDate());

            passenger = passengerService.addPassenger(passenger);

            passengerBean.setId(passenger.getId());
            passengerBean.setLastName(passenger.getLastName());
            passengerBean.setFirstName(passenger.getFirstName());
            passengerBean.setDocNumber(passenger.getDocNumber());
            passengerBean.setBirthDate(passenger.getBirthDate());

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
     * @return result of processing
     */
    public PassengerBean getPassenger(PassengerBean passengerBean) {
        try {
            Passenger passenger = commonService.findPassenger(passengerBean.getDocNumber());

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
     * Searches passenger by document number, searches train by train number specified in param, and then purchases ticket for passenger
     * only if train has free seats, passengers with same name and birth date is not registered on this train
     * and departure time bigger than current time at least on 10 minutes
     * If error occurs, method will add error message and error flag to output parameter
     *
     * @param  ticketBean
     *         Passenger instance with default id value and specified last name, first name, document number and birth date
     *
     * @return result of processing
     */
    public TicketBean purchaseTicket(TicketBean ticketBean) {
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

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
}
