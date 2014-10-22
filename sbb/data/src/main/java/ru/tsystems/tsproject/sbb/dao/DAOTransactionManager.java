package ru.tsystems.tsproject.sbb.dao;

import javax.persistence.EntityManager;

public class DAOTransactionManager {

    private EntityManager entityManager;

    public DAOTransactionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
