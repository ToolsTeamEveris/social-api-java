package com.social.manager;

public interface Manager<E> {
	public Iterable<E> findAll();
	public E findById(E e);
	public void save(E e);
	public void save(Iterable<E> e);
	public void update(E e);
	public void update(Iterable<E> e);
	public void remove(E e);
}
