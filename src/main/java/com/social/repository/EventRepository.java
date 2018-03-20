package com.social.repository;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
