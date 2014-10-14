package ru.tsystems.tsproject.sbb.daoImpl;

import javax.persistence.EntityManager;


public abstract class AbstractDAOImpl {
	private final EntityManager entityManager;
	
	public AbstractDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
