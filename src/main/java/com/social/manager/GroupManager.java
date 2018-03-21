package com.social.manager;

import com.social.entity.Group;
import com.social.entity.Person;

public interface GroupManager extends Manager<Group> {
	public Group addPersons(Iterable<Person> person);
}
