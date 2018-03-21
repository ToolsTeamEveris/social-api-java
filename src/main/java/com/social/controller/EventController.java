package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Event;
import com.social.entity.Person;
import com.social.manager.EventManager;
import com.social.manager.PersonManager;

@RestController
public class EventController {
	
	private final EventManager eventManager;
	private final PersonManager personManager;
	
	@Autowired
	public EventController(final EventManager eventManager, final PersonManager personManager) {
		this.eventManager = eventManager;
		this.personManager = personManager;
	}
		
	@GetMapping(value="/event")
	@ResponseBody
	public Iterable<Event> getAll() {
		return this.eventManager.findAll();
	}
	
	@GetMapping(value="/event/{id}")
	@ResponseBody
	public Event getById(@PathVariable Long id) {
		return this.eventManager.findById(id);
	}
	
	@PostMapping(value="/event/{eventId}/person/{personId}/add")
	@ResponseBody
	public Event addPerson(@PathVariable Long eventId, @PathVariable Long personId) {
		final Event event = eventManager.findById(eventId);
		final Person person = personManager.findById(personId);
		
		this.eventManager.addPerson(event, person);
		return event;
	}
	
	// TODO
	@GetMapping(value="/event/person/{personId}")
	@ResponseBody
	public List<Event> getByPersonId(@PathVariable Long personId) {
		return this.eventManager.getByCreatorId(personId);
	}
	
	@PostMapping(value="/event")
	@ResponseBody
	public void create(@RequestBody Event event) {
		this.eventManager.save(event);
	}
	
	@DeleteMapping(value="/event/{id}")
	@ResponseBody
	public void remove(@PathVariable Long id) {
		final Event event = eventManager.findById(id);
		this.eventManager.remove(event);
	}
	
}
