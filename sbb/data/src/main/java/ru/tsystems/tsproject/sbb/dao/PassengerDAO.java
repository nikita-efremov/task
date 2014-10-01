package ru.tsystems.tsproject.sbb.dao;

import org.hibernate.Session;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.util.HibernateUtil;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class PassengerDAO {

    public void addPassenger(Passenger passenger) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(passenger);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updatePassenger(int passengerID, Passenger passenger) throws SQLException {
        Session session = null;
        try {
            Passenger passengerInDB = getPassengerById(passengerID);
            passengerInDB.setFirstName(passenger.getFirstName());
            passengerInDB.setLastName(passenger.getLastName());
            passengerInDB.setBirthDate(passenger.getBirthDate());
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(passengerInDB);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Passenger getPassengerById(int passengerID) throws SQLException {
        Session session = null;
        Passenger passenger = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            passenger = (Passenger) session.get(Passenger.class, passengerID);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return passenger;
    }
}
