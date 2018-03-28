package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@GetMapping(value="/event/search/{text}")
	@ResponseBody
	public List<Event> searchPerson(@PathVariable String text) {
		return eventManager.findByCustomText(text);
	}
		
	@GetMapping(value="/event")
	@ResponseBody
	public Iterable<Event> getAll() {
		return eventManager.findAll();
	}
	
	@GetMapping(value="/event/{id}")
	@ResponseBody
	public Event getById(@PathVariable Long id) {
		return eventManager.findById(id);
	}
	
	@PostMapping(value="/event/{eventId}")
	@ResponseBody
	public Event addPerson(@RequestHeader("Authorization") String authHeader,@PathVariable Long eventId) {
		return eventManager.addPerson(authHeader,eventId);	
	}
	
	@GetMapping(value="/event/person/{personId}")
	@ResponseBody
	public List<Event> getByPersonId(@PathVariable Long personId) {
		return this.eventManager.getByCreatorId(personId);
	}
	
	@PostMapping(value="/event")
	@ResponseBody
	public Event create(@RequestHeader("Authorization") String authHeader,@RequestBody Event event) {
		return eventManager.addEvent(authHeader, event);
	}
	
	@PutMapping(value="/event/{id}")
	@ResponseBody
	public Event update(@RequestHeader("Authorization") String authHeader,
						@RequestBody Event newEvent, 
						@PathVariable("id") Long oldEventId) {
		return eventManager.updateEvent(authHeader,oldEventId, newEvent);
	}
	
	@DeleteMapping(value="/event/{id}")
	@ResponseBody
	public Event remove(@RequestHeader("Authorization") String authHeader,
					   @PathVariable Long id) {
		return eventManager.removeEvent(authHeader, id);
	}
	
}
