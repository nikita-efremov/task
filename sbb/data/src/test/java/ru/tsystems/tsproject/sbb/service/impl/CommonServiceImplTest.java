package ru.tsystems.tsproject.sbb.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tsproject.sbb.dao.api.PassengerDAO;
import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.dao.api.TrainDAO;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.PassengerNotExistsException;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.exception.TrainNotExistsException;
import ru.tsystems.tsproject.sbb.service.api.CommonService;

import java.util.Calendar;
import java.util.Date;

/**
 * Test of CommonServiceImpl
 * @author  Nikita Efremov
 * @since   1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CommonServiceImplTest {

    @Mock
    private StationDAO stationDAO = new StationDAOImpl();

    @Mock
    private TrainDAO trainDAO = new TrainDAOImpl();

    @Mock
    private PassengerDAO passengerDAO = new PassengerDAOImpl();

    @InjectMocks
    private CommonService commonServiceImpl = new CommonServiceImpl();

    @Test
    public void testFindStation_SearchSuccess() throws Exception {
        int stationID = 44044;
        String stationName = "TestStation";

        Station stationOutput = new Station();
        stationOutput.setId(stationID);
        stationOutput.setName(stationName);

        when(stationDAO.getStationByName(stationName)).thenReturn(stationOutput);

        Station stationFromMethod = commonServiceImpl.findStation(stationName);
        Assert.assertEquals(stationOutput, stationFromMethod);
    }

    @Test(expected = StationNotExistsException.class)
    public void testFindStation_SearchFail() throws Exception {
        String stationName = "UnknownStation";
        when(stationDAO.getStationByName(stationName)).thenReturn(null);
        commonServiceImpl.findStation(stationName);
    }

    @Test
    public void testFindTrain_SearchSuccess() throws Exception {
        int trainID = 213;
        int seats = 18;
        int totalSeats = 20;
        String trainNumber = "666x";

        Train train = new Train();
        train.setId(trainID);
        train.setNumber(trainNumber);
        train.setSeats(seats);
        train.setTotalSeats(totalSeats);

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(train);
        Train trainFromMethod = commonServiceImpl.findTrain(trainNumber);
        Assert.assertEquals(train, trainFromMethod);
    }

    @Test(expected = TrainNotExistsException.class)
    public void testFindTrain_SearchFail() throws Exception {
        String trainNumber = "666z";
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(null);
        commonServiceImpl.findTrain(trainNumber);
    }

    @Test
    public void testFindPassenger_SearchSuccess() throws Exception {
        int passengerID = 45;
        String docNumber = "1234567890";
        String lastName = "Barrows";
        String firstName = "Lincoln";
        Date birthDate = Calendar.getInstance().getTime();

        Passenger passenger = new Passenger();
        passenger.setId(passengerID);
        passenger.setDocNumber(docNumber);
        passenger.setLastName(lastName);
        passenger.setFirstName(firstName);
        passenger.setBirthDate(birthDate);

        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(passenger);
        Passenger passengerFromMethod = commonServiceImpl.findPassenger(docNumber);
        Assert.assertEquals(passenger, passengerFromMethod);
    }

    @Test(expected = PassengerNotExistsException.class)
    public void testFindPassenger_SearchFail() throws Exception {
        String docNumber = "0987654321";
        when(passengerDAO.getPassengerByDocumentNumber(docNumber)).thenReturn(null);
        commonServiceImpl.findPassenger(docNumber);
    }
}
