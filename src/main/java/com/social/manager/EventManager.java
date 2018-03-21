package com.social.manager;

import java.util.List;

import com.social.entity.Event;
import com.social.entity.Person;

public interface EventManager extends Manager<Event> {
	public void addPerson(Event event, Person person);
	public List<Event> getByCreatorId(Long id);
}
