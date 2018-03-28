package com.social.manager;

import java.util.List;

import com.social.entity.Event;
import com.social.entity.Person;

public interface EventManager extends Manager<Event> {
	public Event addPerson(String authHeader,Long eventId);
	public Event removePerson(String authHeader,Long eventId);
	public Event addEvent(String authHeader, Event event);
	public Event updateEvent(String authHeader,Long oldEventId, Event newEvent);
	public Event removeEvent(String authHeader,Long eventId);
	public List<Event> findByCustomText(String text);
	public List<Event> getByCreatorId(Long id);
}
