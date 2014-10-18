package ru.tsystems.tsproject.sbb.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TicketBean;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Ticket;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.api.CommonService;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;
import ru.tsystems.tsproject.sbb.service.impl.AdministratorServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.CommonServiceImpl;
import ru.tsystems.tsproject.sbb.service.impl.PassengerServiceImpl;

import javax.persistence.EntityManager;

/**
 *
 * Class implements model behaviour of mvc pattern of object passenger
 * @author  Nikita Efremov
 * @since   1.0
 */

public class PassengerModel extends AbstractModel {

    private static final Logger log = Logger.getLogger(PassengerModel.class);

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
        EntityManager entityManager = null;
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
            PassengerService passengerService = new PassengerServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Passenger passenger = new Passenger();
            passenger.setLastName(passengerBean.getLastName());
            passenger.setFirstName(passengerBean.getFirstName());
            passenger.setDocNumber(passengerBean.getDocNumber());
            passenger.setBirthDate(passengerBean.getBirthDate());

            passengerService.addPassenger(passenger);
            passenger = commonService.findPassenger(passenger);

            passengerBean.setId(passenger.getId());
            passengerBean.setLastName(passenger.getLastName());
            passengerBean.setFirstName(passenger.getFirstName());
            passengerBean.setDocNumber(passenger.getDocNumber());
            passengerBean.setBirthDate(passenger.getBirthDate());

        } catch (PassengerAlreadyRegisteredException e) {
            passengerBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            passengerBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            passengerBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
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
        EntityManager entityManager = null;
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
            PassengerService passengerService = new PassengerServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Passenger passenger = new Passenger();
            passenger.setDocNumber(passengerBean.getDocNumber());

            passenger = commonService.findPassenger(passenger);

            if (passenger == null) {
                throw new PassengerNotRegisteredException("Passenger with document number " + passengerBean.getDocNumber()
                        + " is not registered");
            }

            passengerBean.setId(passenger.getId());
            passengerBean.setLastName(passenger.getLastName());
            passengerBean.setFirstName(passenger.getFirstName());
            passengerBean.setDocNumber(passenger.getDocNumber());
            passengerBean.setBirthDate(passenger.getBirthDate());

        } catch (PassengerNotRegisteredException e) {
            passengerBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (DAOException e) {
            passengerBean.setProcessingErrorMessage("Database error occurred. Error code: " + e.getErrorCode());
            log.log(Level.ERROR, "Database error occurred. Error code: " + e.getErrorCode() + " - " + e);
        } catch (Exception e) {
            passengerBean.setProcessingErrorMessage("Unknown error occurred");
            log.log(Level.ERROR, "Unknown error occurred: " + e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
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
        EntityManager entityManager = null;
        try {
            entityManager = AbstractModel.getEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
            TicketDAO ticketDAO = new TicketDAOImpl(entityManager);
            CommonService commonService = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);
            PassengerService passengerService = new PassengerServiceImpl(stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

            Passenger passenger = new Passenger();
            passenger.setDocNumber(ticketBean.getPassengerDocNumber());
            passenger = commonService.findPassenger(passenger);

            if (passenger == null) {
                throw new PassengerNotRegisteredException("Passenger with document number " + ticketBean.getPassengerDocNumber()
                        + " is not registered");
            }

            Train train = new Train();
            train.setNumber(ticketBean.getTrainNumber());
            train = commonService.findTrain(train);

            if (train == null) {
                throw new TrainNotExistsException("Train with number " + ticketBean.getTrainNumber() + " not exists");
            }

            Ticket ticket = passengerService.purchaseTicket(train, passenger);
            ticketBean.setPassengerDocNumber(ticket.getPassenger().getDocNumber());
            ticketBean.setTrainNumber(ticket.getTrain().getNumber());
            ticketBean.setTicketNumber(ticket.getTicketNumber());
            ticketBean.setId(ticket.getId());

        } catch (PassengerAlreadyRegisteredException e) {
            ticketBean.setProcessingErrorMessage(e.getMessage());
            log.log(Level.ERROR, e);
        } catch (PassengerNotRegisteredException e) {
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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return ticketBean;
    }
}
