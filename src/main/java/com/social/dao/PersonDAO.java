package com.social.dao;

import com.social.entity.Person;

public interface PersonDAO extends EntityDAO<Person>{
	public Person relatePersons(Iterable<Person> person);
}
