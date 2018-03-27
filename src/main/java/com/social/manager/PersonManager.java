package com.social.manager;

import java.util.List;

import com.social.entity.Person;

public interface PersonManager extends Manager<Person> {
	//Person relatePerson(Long id);
	void updatePerson(Long id, Person person);
    Person findByUsername(String username);
	List<Person> findByCustomText(String text);
}
