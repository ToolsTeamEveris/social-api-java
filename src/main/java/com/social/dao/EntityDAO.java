package com.social.dao;


public interface EntityDAO<E> {
	public Iterable<E> findAll();
	public E findById(Long id);
	public void save(E e);
	public void save(Iterable<E> e);
	public void update(E e);
	public void update(Iterable<E> e);
	public void remove(E e);
}
