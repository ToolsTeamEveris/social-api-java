package com.social.manager;

import com.social.entity.Person;

public interface PersonManager extends Manager<Person> {
	Person relatePerson(Long id);
}
