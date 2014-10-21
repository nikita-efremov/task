package ru.tsystems.tsproject.sbb.dao;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 21.10.14
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class DAOTransactionManager {

    private EntityManager entityManager;

    public DAOTransactionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
