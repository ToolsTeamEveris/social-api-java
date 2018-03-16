package com.social.manager;

import com.social.entity.Person;

public interface PersonManager<E> extends Manager<E> {
	public Person relatePerson(Iterable<Person> person);
}
