package ru.tsystems.tsproject.sbb.daoImpl;

import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.util.JPAUtil;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class StationDAOImpl implements StationDAO {

    public void addStation(Station station) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.persist(station);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public Station getStationById(int stationID) {
        EntityManager entityManager = null;
        Station station = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            station = entityManager.find(Station.class, stationID);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return station;
    }

    public void updateStation(Station station) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.merge(station);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public void deleteStation(Station station) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(station) ? station : entityManager.merge(station));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }
}
