package ru.tsystems.tsproject.sbb.daoImpl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.dao.StationDAO;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Train;
import ru.tsystems.tsproject.sbb.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class StationDAOImpl implements StationDAO {

    private static final Logger log = Logger.getLogger(StationDAOImpl.class);

    public void addStation(Station station) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.persist(station);
            entityManager.getTransaction().commit();
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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return station;
    }

    public Station getStationByName(String name) {
        EntityManager entityManager = null;
        Station station = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            Query query = entityManager.createQuery("SELECT s FROM Station s where s.name = :name")
                    .setParameter("name", name);
            List stations = query.getResultList();
            if (stations.size() != 0) {
                station = (Station)stations.get(0);
            }
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return station;
    }

    public Collection getAllStations() {
        EntityManager entityManager = null;
        List stations = new ArrayList<Station>();
        try {
            entityManager = JPAUtil.getEntityManger();
            Query query = entityManager.createQuery("SELECT s FROM Station s");
            stations = query.getResultList();
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return stations;
    }

    public void updateStation(Station station) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.merge(station);
            entityManager.getTransaction().commit();
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
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }
}
