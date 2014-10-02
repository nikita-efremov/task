package ru.tsystems.tsproject.sbb.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class JPAUtil {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");

    public static EntityManager getEntityManger() {
        return entityManagerFactory.createEntityManager();
    }
}
