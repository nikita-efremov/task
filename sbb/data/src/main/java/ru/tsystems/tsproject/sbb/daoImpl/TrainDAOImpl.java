package ru.tsystems.tsproject.sbb.daoImpl;


import ru.tsystems.tsproject.sbb.dao.TrainDAO;
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
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class TrainDAOImpl implements TrainDAO {

    public void addTrain(Train train) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.persist(train);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public Train getTrainByID(int trainID)  {
        EntityManager entityManager = null;
        Train train = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            train = entityManager.find(Train.class, trainID);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return train;
    }

    public void decreaseSeatAmount(int trainID) {
        Train train = getTrainByID(trainID);
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            train.setSeats(train.getSeats() - 1);
            entityManager.merge(train);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public Collection getAllTrains() {
        EntityManager entityManager = null;
        List trains = new ArrayList<Train>();
        try {
            entityManager = JPAUtil.getEntityManger();
            Query query = entityManager.createQuery("SELECT e FROM Train e");
            trains = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return trains;
    }
}
