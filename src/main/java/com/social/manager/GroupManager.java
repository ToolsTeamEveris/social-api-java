package com.social.manager;

import com.social.entity.Group;
import com.social.entity.Person;

public interface GroupManager<E> extends Manager<E> {
	public Group addPersons(Iterable<Person> person);
}
