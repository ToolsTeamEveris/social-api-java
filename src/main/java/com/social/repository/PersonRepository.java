package com.social.repository;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

}