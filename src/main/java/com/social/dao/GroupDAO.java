package com.social.dao;

import com.social.entity.Group;
import com.social.entity.Person;

public interface GroupDAO extends EntityDAO<Group>{
	public Group addPersons(Iterable<Person> person);
}
