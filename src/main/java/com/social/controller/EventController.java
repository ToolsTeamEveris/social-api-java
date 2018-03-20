package com.social.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Event;
import com.social.manager.EventManager;

@RestController
public class EventController {
	
	private final EventManager manager;
	
	@Autowired
	public EventController(final EventManager eventManager) {
		this.manager = eventManager;
	}
	
	@GetMapping(value="/event")
	@ResponseBody
	public Iterable<Event> getAll() {
		return this.manager.findAll();
	}
	
	@GetMapping(value="/event/{id}")
	@ResponseBody
	public Event getById(@PathVariable Long id) {
		return (Event) this.manager.findById(id);
	}
	
	@PostMapping(value="/event/{eventId}/person/{personId}/add")
	@ResponseBody
	public Event addPerson(@PathVariable Long eventId, @PathVariable Long personId) {
		return new Event();
	}
	
	@GetMapping(value="/event/person/{personId}")
	@ResponseBody
	public List<Event> getByPersonId(@PathVariable Long personId) {
		return new ArrayList<Event>();
	}
	
	@PostMapping(value="/event")
	@ResponseBody
	public void create(@RequestBody Event event) {
		this.manager.save(event);
	}
	
	@DeleteMapping(value="/event/{id}")
	@ResponseBody
	public void remove(@PathVariable Long id) {
		final Event event = manager.findById(id);
		this.manager.remove(event);
	}
	
	
	
	
	
}
