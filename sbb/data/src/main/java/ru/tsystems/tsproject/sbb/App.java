package ru.tsystems.tsproject.sbb;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.dao.api.*;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.*;
import ru.tsystems.tsproject.sbb.service.api.AdministratorService;
import ru.tsystems.tsproject.sbb.service.impl.AdministratorServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
    
    private static final Logger log = Logger.getLogger(App.class);
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");

    public static EntityManager getEntityManger() {
        return entityManagerFactory.createEntityManager();
    }
            
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
		try {
			PassengerDAO passengerDAO = new PassengerDAOImpl(getEntityManger());

			Passenger passenger = passengerDAO.get(2);
			log.log(Level.INFO, passenger.getLastName());
			log.log(Level.INFO, passenger.getFirstName());
			log.log(Level.INFO, passenger.getTickets().size());
		} catch (Exception e) {
			log.log(Level.ERROR, e);		
		}

    }

    public static void addTrain() {
		try {
			TrainDAO trainDAO = new TrainDAOImpl(getEntityManger());

			Train train = new Train();
			train.setSeats(344);
			train.setNumber("230z");
			trainDAO.create(train);
		} catch (Exception e) {
			log.log(Level.ERROR, e);		
		}
    }

    public static void addTimetable() {
        try {
			TimetableDAO timetableDAO = new TimetableDAOImpl(getEntityManger());
			StationDAO stationDAO = new StationDAOImpl(getEntityManger());
			TrainDAO trainDAO = new TrainDAOImpl(getEntityManger());

			Calendar calendar = Calendar.getInstance();
			calendar.set(2014, Calendar.OCTOBER, 13, 6, 55);

			Timetable timetable = new Timetable();
			timetable.setStation(stationDAO.<Station>get(1));
			timetable.setTrain(trainDAO.<Train>get(3));
			timetable.setDate(calendar.getTime());
			timetableDAO.create(timetable);
		} catch (Exception e) {
			log.log(Level.ERROR, e);		
		}
    }

    public static void getTrainsByStation() {
        try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

			TimetableDAO timetableDAO = new TimetableDAOImpl(getEntityManger());
			StationDAO stationDAO = new StationDAOImpl(getEntityManger());

			Collection<Timetable> collection = timetableDAO.getTimetableByStation(1);
			for (Timetable timetable: collection) {
				log.log(Level.INFO, "train: " + timetable.getTrain().getNumber() + "-" + timetable.getTrain().getSeats());
				log.log(Level.INFO, "time: " + simpleDateFormat.format(timetable.getDate().getTime()));
				log.log(Level.INFO, "");
			}
		} catch (Exception e) {
			log.log(Level.ERROR, e);		
		}
    }

    public static void getTrainsByStationAndDate() {
        try {
			SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

			TimetableDAO timetableDAO = new TimetableDAOImpl(getEntityManger());
			StationDAO stationDAO = new StationDAOImpl(getEntityManger());
            TrainDAO trainDAO = new TrainDAOImpl(getEntityManger());

			Calendar calendar = Calendar.getInstance();
			calendar.set(2010, Calendar.OCTOBER, 13, 6, 55);
			Date dateStart = calendar.getTime();
			calendar.set(2014, Calendar.OCTOBER, 18, 6, 55);
			Date dateEnd = calendar.getTime();

			Collection<Train> collection = trainDAO.getTrainsByStationsAndDate(
                1,
                2,
                dateStart,
                dateEnd);
			for (Train train: collection) {
				log.log(Level.INFO, "train: " + train.getNumber() + "-" + train.getSeats());
				log.log(Level.INFO, "");

			}
		} catch (Exception e) {
			log.log(Level.ERROR, e);		
		}
    }

    public static void adminService() {
		try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            StationDAO stationDAO = new StationDAOImpl(entityManager);
            PassengerDAO passengerDAO = new PassengerDAOImpl(entityManager);
            TrainDAO trainDAO = new TrainDAOImpl(entityManager);
            TimetableDAO timetableDAO = new TimetableDAOImpl(entityManager);
			AdministratorService administratorService = new AdministratorServiceImpl(
                    stationDAO, trainDAO, passengerDAO, timetableDAO);
			Station station = new Station();
			station.setName("Pskov");
            administratorService.addStation(station);
        } catch (Exception e) {
            log.log(Level.ERROR, e);
        }
    }
}
