package com.social.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	public List<Event> findByCreatorId(Long id);
}
