package ru.tsystems.tsproject.sbb.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 15.10.14
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractModel {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
