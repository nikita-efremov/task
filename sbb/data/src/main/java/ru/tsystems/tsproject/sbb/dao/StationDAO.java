package ru.tsystems.tsproject.sbb.dao;

import org.hibernate.Session;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.util.HibernateUtil;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class StationDAO {

    public void addStation(Station station) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(station);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Station getStationById(int stationID) throws SQLException {
        Session session = null;
        Station station = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            station = (Station) session.get(Station.class, stationID);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return station;
    }
}
