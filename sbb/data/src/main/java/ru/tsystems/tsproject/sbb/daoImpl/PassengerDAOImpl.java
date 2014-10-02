package ru.tsystems.tsproject.sbb.daoImpl;

import ru.tsystems.tsproject.sbb.dao.PassengerDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.util.JPAUtil;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 01.10.14
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class PassengerDAOImpl implements PassengerDAO {

    public void addPassenger(Passenger passenger) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.persist(passenger);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public void updatePassenger(int passengerID, Passenger passenger) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            entityManager.getTransaction().begin();
            entityManager.merge(passenger);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
    }

    public Passenger getPassengerById(int passengerID) {
        EntityManager entityManager = null;
        Passenger passenger = null;
        try {
            entityManager = JPAUtil.getEntityManger();
            passenger = entityManager.find(Passenger.class, passengerID);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            if ((entityManager != null) && (entityManager.isOpen())) {
                entityManager.close();
            }
        }
        return passenger;
    }
}
