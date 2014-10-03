package ru.tsystems.tsproject.sbb.daoImpl;

import ru.tsystems.tsproject.sbb.dao.TimetableDAO;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
public class TimetableDAOImpl implements TimetableDAO {

    public void addTimetable(Timetable timetable) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.persist(timetable);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public Timetable getTimetableById(int timetableID) {
        EntityManager entityManager = null;
        Timetable Timetable = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            Timetable = entityManager.find(Timetable.class, timetableID);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return Timetable;
    }

    public void updateTimetable(Timetable timetable) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.merge(timetable);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public void deleteTimetable(Timetable timetable) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(timetable) ? timetable : entityManager.merge(timetable));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public Collection getTimetableByStation(Station station) {
        EntityManager entityManager = null;
        List timetableList = new ArrayList<Timetable>();
        try {
            entityManager = JPAUtil.getEntityManger();
            Query query = entityManager.createQuery(
                    " select t "
                            + " from Timetable t INNER JOIN FETCH t.train Train"
                            + " where t.station = :station"
            )
                    .setParameter("station", station);
            timetableList = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return timetableList;
    }

    public Collection getTrainsByStationsAndDate(Station stationStart, Station stationEnd, Date dateStart, Date dateEnd) {
        EntityManager entityManager = null;
        List trainList = new ArrayList<Train>();
        try {
            entityManager = JPAUtil.getEntityManger();
            Query query = entityManager.createQuery(
                    "select t.train"
                            + " from Timetable t INNER JOIN t.train Train"
                            + " where (t.station = :stationStart or t.station = :stationEnd) and (t.date >= :dateStart and t.date <=:dateEnd)"
            )
                    .setParameter("stationStart", stationStart)
                    .setParameter("stationEnd", stationEnd)
                    .setParameter("dateStart", dateStart)
                    .setParameter("dateEnd", dateEnd);

            trainList = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return trainList;
    }
}
