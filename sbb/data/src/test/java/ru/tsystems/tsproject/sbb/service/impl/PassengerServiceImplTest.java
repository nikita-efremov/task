package ru.tsystems.tsproject.sbb.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.*;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;
import static ru.tsystems.tsproject.sbb.service.impl.PassengerServiceImplTestHelper.*;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Test of PassengerServiceImpl
 */

@RunWith(MockitoJUnitRunner.class)
public class PassengerServiceImplTest {

    @Mock
    private StationDAO stationDAO = new StationDAOImpl(null);

    @Mock
    private TrainDAO trainDAO = new TrainDAOImpl(null);

    @Mock
    private PassengerDAO passengerDAO = new PassengerDAOImpl(null);

    @Mock
    private TimetableDAO timetableDAO = new TimetableDAOImpl(null);

    @Mock
    private TicketDAO ticketDAO = new TicketDAOImpl(null);

    @InjectMocks
    private PassengerService passengerService = new PassengerServiceImpl(
            stationDAO, trainDAO, passengerDAO, timetableDAO, ticketDAO);

    @Test(expected = StationNotExistsException.class)
    public void testFindTrainsByStationsAndDate_StartStationNotFound() throws Exception {
        String stationStartName = "Novgorod";
        String stationEndName = "Krasnodar";
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_MONTH, 1);

        when(stationDAO.getStationByName(stationStartName)).thenReturn(null);
        when(stationDAO.getStationByName(stationEndName)).thenReturn(new Station());
        passengerService.findTrainsByStationsAndDate(stationStartName, stationEndName, startCalendar.getTime(), endCalendar.getTime());
    }

    @Test(expected = StationNotExistsException.class)
    public void testFindTrainsByStationsAndDate_EndStationNotFound() throws Exception {
        String stationStartName = "Novgorod";
        String stationEndName = "Krasnodar";
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_MONTH, 1);

        Station startStation = new Station();
        startStation.setName(stationStartName);

        when(stationDAO.getStationByName(stationStartName)).thenReturn(startStation);
        when(stationDAO.getStationByName(stationEndName)).thenReturn(null);
        passengerService.findTrainsByStationsAndDate(stationStartName, stationEndName, startCalendar.getTime(), endCalendar.getTime());
    }

    @Test
    public void testFindTrainsByStationsAndDate_Success() throws Exception {
        int stationStartID = 45;
        int stationEndID = 4;
        String stationStartName = "Novgorod";
        String stationEndName = "Krasnodar";
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_MONTH, 1);

        Station startStation = new Station();
        startStation.setId(stationStartID);
        startStation.setName(stationStartName);

        Station endStation = new Station();
        endStation.setId(stationEndID);
        endStation.setName(stationEndName);

        Collection<Train> sourceTrains = new LinkedList<Train>();
        sourceTrains.add(getAppropriateTrain(startStation, endStation));
        sourceTrains.add(getAppropriateTrain(startStation, endStation));          //copy of appropriate train
        sourceTrains.add(getUnAppropriateTrainType1(startStation, endStation));   //startDate is later than endDate
        sourceTrains.add(getUnAppropriateTrainType2(startStation, endStation));   //startStation missing
        sourceTrains.add(getUnAppropriateTrainType3(startStation, endStation));   //endStation missing

        Collection<Train> expectedTrains = new LinkedList<Train>();
        expectedTrains.add(getAppropriateTrain(startStation, endStation));

        when(stationDAO.getStationByName(stationStartName)).thenReturn(startStation);
        when(stationDAO.getStationByName(stationEndName)).thenReturn(endStation);
        when(trainDAO.getTrainsByStationsAndDate(stationStartID, stationEndID, startCalendar.getTime(), endCalendar.getTime()))
                .thenReturn(sourceTrains);
        Collection<Train> actualTrains = passengerService.findTrainsByStationsAndDate(
                stationStartName, stationEndName, startCalendar.getTime(), endCalendar.getTime());
        verify(trainDAO).getTrainsByStationsAndDate(stationStartID, stationEndID, startCalendar.getTime(), endCalendar.getTime());
        Assert.assertTrue(expectedTrains.containsAll(actualTrains));
        Assert.assertEquals(expectedTrains.size(), actualTrains.size());
    }

    @Test(expected = PassengerAlreadyExistsException.class)
    public void testAddPassenger_AlreadyExists() throws Exception {
        String docNumber = "1234567890";
        Passenger passenger = new Passenger();
        passenger.setDocNumber(docNumber);
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(passenger);
        passengerService.addPassenger(passenger);
    }

    @Test
    public void testAddPassenger_success() throws Exception {
        int passengerID = 45;
        String docNumber = "1234567890";
        String lastName = "Barrows";
        String firstName = "Lincoln";
        Date birthDate = Calendar.getInstance().getTime();

        Passenger sourcePassenger = new Passenger();
        sourcePassenger.setDocNumber(docNumber);
        sourcePassenger.setLastName(lastName);
        sourcePassenger.setFirstName(firstName);
        sourcePassenger.setBirthDate(birthDate);

        Passenger expectedPassenger = new Passenger();
        expectedPassenger.setId(passengerID);
        expectedPassenger.setDocNumber(docNumber);
        expectedPassenger.setLastName(lastName);
        expectedPassenger.setFirstName(firstName);
        expectedPassenger.setBirthDate(birthDate);

        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(null, expectedPassenger);
        Passenger resultPassenger = passengerService.addPassenger(sourcePassenger);
        verify(passengerDAO).create(sourcePassenger);
        Assert.assertEquals(expectedPassenger, resultPassenger);
    }

    @Test(expected = PassengerNotExistsException.class)
    public void testPurchaseTicket_PassengerNotFound() throws Exception {
        String docNumber = "1234567890";
        String trainNumber = "34fs";
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(null);
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(new Train());
        passengerService.purchaseTicket(trainNumber, docNumber);
    }

    @Test(expected = TrainNotExistsException.class)
    public void testPurchaseTicket_TrainNotFound() throws Exception {
        String docNumber = "1234567890";
        String trainNumber = "34fs";
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(new Passenger());
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(null);
        passengerService.purchaseTicket(trainNumber, docNumber);
    }

    @Test(expected = TrainAlreadyFullException.class)
    public void testPurchaseTicket_TrainFull() throws Exception {
        String docNumber = "1234567890";
        String trainNumber = "34fs";
        Train train = new Train();
        train.setNumber(trainNumber);
        train.setSeats(0);
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(new Passenger());
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(train);
        passengerService.purchaseTicket(trainNumber, docNumber);
    }

    @Test(expected = TrainAlreadyDepartedException.class)
    public void testPurchaseTicket_TrainDeparted() throws Exception {
        int passengerID = 45;
        String docNumber = "1234567890";
        String lastName = "Barrows";
        String firstName = "Lincoln";
        String trainNumber = "34fs";
        Date birthDate = Calendar.getInstance().getTime();

        Passenger sourcePassenger = new Passenger();
        sourcePassenger.setId(passengerID);
        sourcePassenger.setDocNumber(docNumber);
        sourcePassenger.setLastName(lastName);
        sourcePassenger.setFirstName(firstName);
        sourcePassenger.setBirthDate(birthDate);

        Timetable timetable = new Timetable();
        Calendar depDateCalendar = Calendar.getInstance();
        depDateCalendar.add(Calendar.MINUTE, -9);
        timetable.setDate(depDateCalendar.getTime());

        Train train = new Train();
        train.addTimetable(timetable);
        train.setNumber(trainNumber);
        train.setSeats(1);

        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(sourcePassenger);
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(train);
        passengerService.purchaseTicket(trainNumber, docNumber);
    }

    @Test(expected = PassengerAlreadyRegisteredOnTrainException.class)
    public void testPurchaseTicket_AlreadyRegistered() throws Exception {
        int passengerID = 45;
        String docNumber = "1234567890";
        String lastName = "Barrows";
        String firstName = "Lincoln";
        int trainID = 24;
        String trainNumber = "34fs";
        Date birthDate = Calendar.getInstance().getTime();

        Passenger sourcePassenger = new Passenger();
        sourcePassenger.setId(passengerID);
        sourcePassenger.setDocNumber(docNumber);
        sourcePassenger.setLastName(lastName);
        sourcePassenger.setFirstName(firstName);
        sourcePassenger.setBirthDate(birthDate);

        Passenger anotherPassenger = new Passenger();
        anotherPassenger.setId(passengerID + 1);
        anotherPassenger.setDocNumber(docNumber + "0");
        anotherPassenger.setLastName(lastName);
        anotherPassenger.setFirstName(firstName);
        anotherPassenger.setBirthDate(birthDate);

        Train sourceTrain = new Train();
        sourceTrain.setId(trainID);
        sourceTrain.setNumber(trainNumber);
        sourceTrain.setSeats(1);

        Collection<Passenger> passengers = new LinkedList<Passenger>();
        passengers.add(anotherPassenger);

        when(passengerDAO.getPassengersByTrain(trainID)).thenReturn(passengers);
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(sourcePassenger);
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(sourceTrain);
        passengerService.purchaseTicket(trainNumber, docNumber);
    }

    @Test
    public void testPurchaseTicket_SuccessAnotherLastName() throws Exception {
        int passengerID = 45;
        String docNumber = "1234567890";
        String lastName = "Barrows";
        String firstName = "Lincoln";
        int trainID = 24;
        String trainNumber = "34fs";
        Date birthDate = Calendar.getInstance().getTime();

        Passenger sourcePassenger = new Passenger();
        sourcePassenger.setId(passengerID);
        sourcePassenger.setDocNumber(docNumber);
        sourcePassenger.setLastName(lastName);
        sourcePassenger.setFirstName(firstName);
        sourcePassenger.setBirthDate(birthDate);

        Passenger anotherPassenger = new Passenger();
        anotherPassenger.setId(passengerID + 1);
        anotherPassenger.setDocNumber(docNumber + "0");
        anotherPassenger.setLastName(lastName + "h");
        anotherPassenger.setFirstName(firstName);
        anotherPassenger.setBirthDate(birthDate);

        Train sourceTrain = new Train();
        sourceTrain.setId(trainID);
        sourceTrain.setNumber(trainNumber);
        sourceTrain.setSeats(1);

        Collection<Passenger> passengers = new LinkedList<Passenger>();
        passengers.add(anotherPassenger);

        long ticketNumber = trainID * 1000000 + passengerID;
        Ticket ticket = new Ticket();
        ticket.setTrain(sourceTrain);
        ticket.setPassenger(sourcePassenger);
        ticket.setTicketNumber(ticketNumber);
        ticketDAO.create(ticket);

        when(passengerDAO.getPassengersByTrain(trainID)).thenReturn(passengers);
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(sourcePassenger);
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(sourceTrain);
        passengerService.purchaseTicket(trainNumber, docNumber);
        verify(ticketDAO).create(ticket);
        verify(trainDAO).decreaseSeatAmount(trainID);
    }

    @Test
    public void testPurchaseTicket_SuccessAnotherFirstName() throws Exception {
        int passengerID = 45;
        String docNumber = "1234567890";
        String lastName = "Barrows";
        String firstName = "Lincoln";
        int trainID = 24;
        String trainNumber = "34fs";
        Date birthDate = Calendar.getInstance().getTime();

        Passenger sourcePassenger = new Passenger();
        sourcePassenger.setId(passengerID);
        sourcePassenger.setDocNumber(docNumber);
        sourcePassenger.setLastName(lastName);
        sourcePassenger.setFirstName(firstName);
        sourcePassenger.setBirthDate(birthDate);

        Passenger anotherPassenger = new Passenger();
        anotherPassenger.setId(passengerID + 1);
        anotherPassenger.setDocNumber(docNumber + "0");
        anotherPassenger.setLastName(lastName);
        anotherPassenger.setFirstName(firstName + "h");
        anotherPassenger.setBirthDate(birthDate);

        Train sourceTrain = new Train();
        sourceTrain.setId(trainID);
        sourceTrain.setNumber(trainNumber);
        sourceTrain.setSeats(1);

        Collection<Passenger> passengers = new LinkedList<Passenger>();
        passengers.add(anotherPassenger);

        long ticketNumber = trainID * 1000000 + passengerID;
        Ticket ticket = new Ticket();
        ticket.setTrain(sourceTrain);
        ticket.setPassenger(sourcePassenger);
        ticket.setTicketNumber(ticketNumber);
        ticketDAO.create(ticket);

        when(passengerDAO.getPassengersByTrain(trainID)).thenReturn(passengers);
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(sourcePassenger);
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(sourceTrain);
        passengerService.purchaseTicket(trainNumber, docNumber);
        verify(ticketDAO).create(ticket);
        verify(trainDAO).decreaseSeatAmount(trainID);
    }

    @Test
    public void testPurchaseTicket_SuccessAnotherBirthDate() throws Exception {
        int passengerID = 45;
        String docNumber = "1234567890";
        String lastName = "Barrows";
        String firstName = "Lincoln";
        int trainID = 24;
        String trainNumber = "34fs";
        Date birthDate = Calendar.getInstance().getTime();

        Passenger sourcePassenger = new Passenger();
        sourcePassenger.setId(passengerID);
        sourcePassenger.setDocNumber(docNumber);
        sourcePassenger.setLastName(lastName);
        sourcePassenger.setFirstName(firstName);
        sourcePassenger.setBirthDate(birthDate);

        Passenger anotherPassenger = new Passenger();
        anotherPassenger.setId(passengerID + 1);
        anotherPassenger.setDocNumber(docNumber + "0");
        anotherPassenger.setLastName(lastName);
        anotherPassenger.setFirstName(firstName);
        Calendar calendarForAnotherDate = Calendar.getInstance();
        calendarForAnotherDate.add(Calendar.MINUTE, 1);
        anotherPassenger.setBirthDate(calendarForAnotherDate.getTime());

        Train sourceTrain = new Train();
        sourceTrain.setId(trainID);
        sourceTrain.setNumber(trainNumber);
        sourceTrain.setSeats(1);

        Collection<Passenger> passengers = new LinkedList<Passenger>();
        passengers.add(anotherPassenger);

        long ticketNumber = trainID * 1000000 + passengerID;
        Ticket ticket = new Ticket();
        ticket.setTrain(sourceTrain);
        ticket.setPassenger(sourcePassenger);
        ticket.setTicketNumber(ticketNumber);
        ticketDAO.create(ticket);

        when(passengerDAO.getPassengersByTrain(trainID)).thenReturn(passengers);
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(sourcePassenger);
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(sourceTrain);
        passengerService.purchaseTicket(trainNumber, docNumber);
        verify(ticketDAO).create(ticket);
        verify(trainDAO).decreaseSeatAmount(trainID);
    }
}
