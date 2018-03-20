package com.social.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Event;
import com.social.repository.EventRepository;


@Service
public class EventManagerImpl implements EventManager {

	private final EventRepository eventRepository;
	
	@Autowired
	public EventManagerImpl(final EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public Iterable<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Event findById(Long id) {
		if (eventRepository.findById(id).isPresent())
			return eventRepository.findById(id).get();
		else
			return null;
	}

	@Override
	public void save(Event newEvent) {
		eventRepository.save(newEvent);
		
	}

	@Override
	public void save(Iterable<Event> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Event e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Iterable<Event> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Event e) {
		eventRepository.delete(e);
		
	}

}
