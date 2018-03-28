package com.social.manager;


import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import javax.activity.InvalidActivityException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Event;
import com.social.entity.Person;
import com.social.repository.EventRepository;

import helper.AuthToken;


@Service
public class EventManagerImpl implements EventManager {

	private final EventRepository eventRepository;
	private final PersonManagerImpl personManager;
	private Person user_logged;
	
	@PersistenceContext
    private EntityManager em;
	
	CriteriaBuilder cb;
	CriteriaQuery<Event> criteriaQuery;
	Root<Event> root;
	
	@Autowired
	public EventManagerImpl(final EventRepository eventRepository,
				final PersonManagerImpl personManager) {
		this.eventRepository = eventRepository;
		this.personManager = personManager;
	}

	@Override
	public List<Event> findByCustomText(String text) {
		cb = em.getCriteriaBuilder();
		criteriaQuery = cb.createQuery(Event.class);
		root = criteriaQuery.from(Event.class);
		
		Expression<String> path = root.get("creator").get("username");
		path = cb.upper(path);
		Expression<String> pathName = root.get("creator").get("name");
		pathName = cb.upper(pathName);
		Expression<String> pathSurname = root.get("name");
		pathSurname = cb.upper(pathSurname);
		Predicate predicate = cb.or(
				cb.like(path, "%"+text.toUpperCase()+"%"), 
				cb.like(pathName, "%"+text.toUpperCase()+"%"),
				cb.like(pathSurname, "%"+text.toUpperCase()+"%")
				);
		criteriaQuery.select(root).where(predicate);
		Query<Event> query = (Query<Event>) em.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
	@Override
	public Event updateEvent(String authHeader,Long oldEventId, Event newEvent) {
		String user_str = AuthToken.getAuthenticatedUser(authHeader);
		user_logged = personManager.findByUsername(user_str);
		
		Event oldEvent = findById(oldEventId);
		
		if((oldEvent != null) && (oldEvent.getCreator() == user_logged)) {
			oldEvent.setStartingDate(newEvent.getStartingDate());
			oldEvent.setEndingDate(newEvent.getEndingDate());
			oldEvent.setName(newEvent.getName());
			
			update(oldEvent);
		}else {
			oldEvent = null;
		}
		
		return oldEvent;
	}
	
	@Override
	public Event addPerson(String authHeader,Long eventId) {
		String user_str = AuthToken.getAuthenticatedUser(authHeader);
		user_logged = personManager.findByUsername(user_str);
		Event event = findById(eventId);
		if(event != null) {
			List<Person> list = event.getAssistants();
			list.add(user_logged);
			event.setAssistants(list);
			save(event);
			return event;
		}else {
			return null;
		}
	}
	
	@Override
	public Event removePerson(String authHeader, Long eventId) {
		String user_str = AuthToken.getAuthenticatedUser(authHeader);
		user_logged = personManager.findByUsername(user_str);
		
		Event event = findById(eventId);
		if(event != null) {
			List<Person> list = event.getAssistants();
			if(list.remove(user_logged)) {
				event.setAssistants(list);
				save(event);
				return event;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	@Override
	public Event addEvent(String authHeader, Event event) {
		String user_str = AuthToken.getAuthenticatedUser(authHeader);
		user_logged = personManager.findByUsername(user_str);
		
		event.setCreator(user_logged);
		save(event);
		return event;
	}
	
	@Override
	public Event removeEvent(String authHeader, Long eventId) {
		String user_str = AuthToken.getAuthenticatedUser(authHeader);
		user_logged = personManager.findByUsername(user_str);
		
		Event event = findById(eventId);
		
		if((event != null) && (event.getCreator() == user_logged)) {
			remove(event);
		}else {
			event = null;
		}
		
		return event;
	}
	
	@Override
	public void update(Event event) {
		save(event);
	}
	
	public List<Event> getByCreatorId(Long id) {
		return eventRepository.findByCreatorId(id);
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
	public void update(Iterable<Event> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Event e) {
		eventRepository.delete(e);
		
	}
}
