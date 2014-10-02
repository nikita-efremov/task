package ru.tsystems.tsproject.sbb.dao;

import org.hibernate.Session;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class TrainDAO {

    public void addTrain(Train train) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(train);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((session !=null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    public Train getTrainByID(int trainID) throws SQLException {
        Session session = null;
        Train train = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            train = (Train) session.get(Train.class, trainID);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return train;
    }

    public void decreaseSeatAmount(int trainID) throws SQLException {
        Train train = getTrainByID(trainID);
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            train.setSeats(train.getSeats() - 1);
            session.update(train);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getAllTrains() throws SQLException {
        Session session = null;
        List trains = new ArrayList<Train>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trains = session.createCriteria(Train.class).list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return trains;
    }
}
