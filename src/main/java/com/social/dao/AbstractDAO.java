package com.social.dao;

import javax.persistence.EntityManager;

public abstract class AbstractDAO<E> implements EntityDAO<E> {
	private Class<E> persistentClass;
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public Iterable<E> findAll() {
		return null;
	}
	
	public E findById(Long id) {
		return null;
	}
	
	public void save(E e) {
		
	}
	
	public void save(Iterable<E> e) {
		
	}
	
	public void update(E e) {
		
	}
	
	public void update(Iterable<E> e) {
		
	}
	
	public void remove(E e) {
		
	}

}
