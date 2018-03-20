package com.social.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.entity.Event;
import com.social.manager.EventManagerImpl;

@Controller
public class EventController {
	private EventManagerImpl manager;
	
	@Autowired
	public EventController(final EventManagerImpl eventManager) {
		this.manager = eventManager;
	}
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
	@ResponseBody
	public List<Event> getAll() {
		return new ArrayList<Event>();
	}
	
	@RequestMapping(value="/event/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Event getById(@PathVariable Long id) {
		return new Event();
	}
	
	@RequestMapping(value="/event/{eventId}/person/{personId}/add", method=RequestMethod.POST)
	@ResponseBody
	public Event addPerson(@PathVariable Long eventId, @PathVariable Long personId) {
		return new Event();
	}
	
	@RequestMapping(value="/event/person/{personId}", method=RequestMethod.GET)
	@ResponseBody
	public List<Event> getByPersonId(@PathVariable Long personId) {
		return new ArrayList<Event>();
	}
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	@ResponseBody
	public Event create(@RequestBody Event event) {
		return new Event();
	}
	
	@RequestMapping(value="/event/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void remove(@RequestBody Long id) {
		
	}
	
	
	
	
	
}
