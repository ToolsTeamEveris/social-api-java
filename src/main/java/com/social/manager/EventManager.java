package com.social.manager;

import com.social.entity.Event;
import com.social.entity.Person;

public interface EventManager extends Manager<Event> {
	public void addPerson(Event event, Person person);
}
