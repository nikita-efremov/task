package ru.tsystems.tsproject.sbb.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tsproject.sbb.dao.DAOTransactionManager;
import ru.tsystems.tsproject.sbb.dao.api.PassengerDAO;
import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.dao.api.TimetableDAO;
import ru.tsystems.tsproject.sbb.dao.api.TrainDAO;
import ru.tsystems.tsproject.sbb.dao.impl.PassengerDAOImpl;
import ru.tsystems.tsproject.sbb.dao.impl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.dao.impl.TimetableDAOImpl;
import ru.tsystems.tsproject.sbb.dao.impl.TrainDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.exception.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;

import java.util.Calendar;
import java.util.Date;

/**
 * Test of AdministratorServiceImpl
 */

@RunWith(MockitoJUnitRunner.class)
public class AdministratorServiceImplTest {

    @Mock
    private DAOTransactionManager daoTransactionManager = new DAOTransactionManager(null);

    @Mock
    private StationDAO stationDAO = new StationDAOImpl(null);

    @Mock
    private TrainDAO trainDAO = new TrainDAOImpl(null);

    @Mock
    private PassengerDAO passengerDAO = new PassengerDAOImpl(null);

    @Mock
    private TimetableDAO timetableDAO = new TimetableDAOImpl(null);

    @InjectMocks
    private AdministratorService administratorService = new AdministratorServiceImpl(daoTransactionManager,
            stationDAO, trainDAO, passengerDAO, timetableDAO);

    @Test
    public void testAddStation_Success() throws Exception {
        String stationName = "Berlin";
        Station sourceStation = new Station();
        sourceStation.setName(stationName);

        Station expectedStation = new Station();
        expectedStation.setId(5);
        expectedStation.setName(stationName);

        when(stationDAO.getStationByName(stationName)).thenReturn(null, expectedStation);
        Station createdStation = administratorService.addStation(sourceStation);
        verify(stationDAO).create(sourceStation);
        Assert.assertEquals(expectedStation, createdStation);
    }

    @Test(expected = StationAlreadyExistsException.class)
    public void testAddStation_AlreadyExists() throws Exception {
        String stationName = "Berlin";
        Station sourceStation = new Station();
        sourceStation.setName(stationName);

        Station notExpectedStation = new Station();
        notExpectedStation.setId(5);
        notExpectedStation.setName(stationName);

        when(stationDAO.getStationByName(stationName)).thenReturn(notExpectedStation);
        administratorService.addStation(sourceStation);
    }

    @Test
    public void testAddTrain_Success() throws Exception {
        String trainNumber = "545d";
        int trainID = 4;
        int seats = 5;
        int totalSeats = 5;

        Train sourceTrain = new Train();
        sourceTrain.setNumber(trainNumber);

        Train expectedTrain = new Train();
        expectedTrain.setId(trainID);
        expectedTrain.setNumber(trainNumber);
        expectedTrain.setSeats(seats);
        expectedTrain.setTotalSeats(totalSeats);

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(null, expectedTrain);
        Train createdTrain = administratorService.addTrain(sourceTrain);
        verify(trainDAO).create(sourceTrain);
        Assert.assertEquals(expectedTrain, createdTrain);
    }

    @Test(expected = TrainAlreadyExistsException.class)
    public void testAddTrain_AlreadyExists() throws Exception {
        int trainID = 4;
        String trainNumber = "43f";

        Train sourceTrain = new Train();
        sourceTrain.setNumber(trainNumber);

        Train unExpectedTrain = new Train();
        unExpectedTrain.setId(trainID);
        unExpectedTrain.setNumber(trainNumber);

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(unExpectedTrain);
        administratorService.addTrain(sourceTrain);
    }


    @Test(expected = StationNotExistsException.class)
    public void testAddTimetable_StationNotExists() throws Exception {
        String stationName = "Saratov";
        String trainNumber = "345d";

        Date departureDate = Calendar.getInstance().getTime();

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(new Train());
        when(stationDAO.getStationByName(stationName)).thenReturn(null);
        administratorService.addTimetable(trainNumber, stationName, departureDate);
    }

    @Test(expected = TrainNotExistsException.class)
    public void testAddTimetable_TrainNotExists() throws Exception {
        String stationName = "Saratov";
        String trainNumber = "345d";

        Date departureDate = Calendar.getInstance().getTime();

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(null);
        administratorService.addTimetable(trainNumber, stationName, departureDate);
    }

    @Test(expected = TrainStopAlreadyExistsException.class)
    public void testAddTimetable_StopAlreadyExists() throws Exception {
        String stationName = "Saratov";
        String trainNumber = "345d";

        Station expectedStation = new Station();
        expectedStation.setName(stationName);

        Train expectedTrain = new Train();
        expectedTrain.setNumber(trainNumber);

        Timetable timetable = new Timetable();
        timetable.setStation(expectedStation);
        timetable.setTrain(expectedTrain);
        timetable.setDate(new Date());
        expectedTrain.addTimetable(timetable);

        Date departureDate = Calendar.getInstance().getTime();

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(expectedTrain);
        when(stationDAO.getStationByName(stationName)).thenReturn(expectedStation);
        administratorService.addTimetable(trainNumber, stationName, departureDate);
    }

    @Test
    public void testAddTimetable_Success() throws Exception {
        int stationID = 45;
        int trainID = 4576;
        String stationName = "Saratov";
        String trainNumber = "345d";
        Date departureDate = Calendar.getInstance().getTime();

        Station expectedStation = new Station();
        expectedStation.setId(stationID);
        expectedStation.setName(stationName);

        Train expectedTrain = new Train();
        expectedTrain.setId(trainID);
        expectedTrain.setNumber(trainNumber);

        Timetable expectedTimetable = new Timetable();
        expectedTimetable.setStation(expectedStation);
        expectedTimetable.setTrain(expectedTrain);
        expectedTimetable.setDate(new Date());

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(expectedTrain);
        when(stationDAO.getStationByName(stationName)).thenReturn(expectedStation);
        administratorService.addTimetable(trainNumber, stationName, departureDate);
        verify(timetableDAO).create(expectedTimetable);
    }

    @Test(expected = TrainNotExistsException.class)
    public void testGetPassengersByTrain_TrainNotFound() throws Exception {
        String trainNumber = "456f";
        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(null);
        administratorService.getPassengersByTrain(trainNumber);
    }

    @Test
    public void testGetPassengersByTrain() throws Exception {
        int trainID = 456;
        String trainNumber = "456f";

        Train expectedTrain = new Train();
        expectedTrain.setId(trainID);
        expectedTrain.setNumber(trainNumber);

        when(trainDAO.getTrainByNumber(trainNumber)).thenReturn(expectedTrain);
        administratorService.getPassengersByTrain(trainNumber);
        verify(passengerDAO).getPassengersByTrain(expectedTrain.getId());
    }

    @Test
    public void testGetAllTrains() throws Exception {
        administratorService.getAllStations();
        verify(stationDAO).getAll();
    }

    @Test
    public void testGetAllStations() throws Exception {
        administratorService.getAllTrains();
        verify(trainDAO).getAll();
    }
}
