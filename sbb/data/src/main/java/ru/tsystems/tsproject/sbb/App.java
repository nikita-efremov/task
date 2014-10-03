package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.dao.TimetableDAO;
import ru.tsystems.tsproject.sbb.dao.TrainDAO;
import ru.tsystems.tsproject.sbb.daoImpl.*;
import ru.tsystems.tsproject.sbb.entity.*;
import ru.tsystems.tsproject.sbb.service.AdministratorService;
import ru.tsystems.tsproject.sbb.serviceImpl.AdministratorServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //passengerFetch();
        //addTrain();
        //addTimetable();
        //getTrainsByStation();
        //getTrainsByStationAndDate();
        adminService();
    }

    public static void passengerFetch() {
        PassengerDAOImpl passengerDAOImpl = new PassengerDAOImpl();

        Passenger passenger = passengerDAOImpl.getPassengerById(2);
        System.out.println(passenger.getLastName());
        System.out.println(passenger.getFirstName());
        System.out.println(passenger.getTickets().size());

    }

    public static void addTrain() {
        TrainDAO trainDAO = new TrainDAOImpl();

        Train train = new Train();
        train.setSeats(344);
        train.setNumber("230z");
        trainDAO.addTrain(train);
    }

    public static void addTimetable() {
        TimetableDAO timetableDAO = new TimetableDAOImpl();
        StationDAO stationDAO = new StationDAOImpl();
        TrainDAO trainDAO = new TrainDAOImpl();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.OCTOBER, 13, 6, 55);

        Timetable timetable = new Timetable();
        timetable.setStation(stationDAO.getStationById(1));
        timetable.setTrain(trainDAO.getTrainByID(3));
        timetable.setDate(calendar.getTime());

        timetableDAO.addTimetable(timetable);
    }

    public static void getTrainsByStation() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        TimetableDAO timetableDAO = new TimetableDAOImpl();
        StationDAO stationDAO = new StationDAOImpl();

        Collection<Timetable> collection = timetableDAO.getTimetableByStation(stationDAO.getStationById(1));
        for (Timetable timetable: collection) {
            System.out.println("train: " + timetable.getTrain().getNumber() + "-" + timetable.getTrain().getSeats());
            System.out.println("time: " + simpleDateFormat.format(timetable.getDate().getTime()));
            System.out.println();

        }
    }

    public static void getTrainsByStationAndDate() {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        TimetableDAO timetableDAO = new TimetableDAOImpl();
        StationDAO stationDAO = new StationDAOImpl();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.OCTOBER, 13, 6, 55);
        Date dateStart = calendar.getTime();
        calendar.set(2014, Calendar.OCTOBER, 18, 6, 55);
        Date dateEnd = calendar.getTime();

        Collection<Train> collection = timetableDAO.getTrainsByStationsAndDate(
                stationDAO.getStationById(1),
                stationDAO.getStationById(2),
                dateStart,
                dateEnd);
        for (Train train: collection) {
            System.out.println("train: " + train.getNumber() + "-" + train.getSeats());
            System.out.println();

        }
    }

    public static void adminService() {
        AdministratorService administratorService = new AdministratorServiceImpl();
        Station station = new Station();
        station.setName("Pskov");
        administratorService.addStation(station);
    }
}
