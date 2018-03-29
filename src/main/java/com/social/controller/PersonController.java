package com.social.controller;
import java.util.ArrayList;
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

import com.social.entity.Person;
import com.social.manager.PersonManager;

import helper.AuthToken;

@RestController
public class PersonController  {
	
	private final PersonManager manager;

	@Autowired
	public PersonController(final PersonManager personManager) {
		this.manager = personManager;
	}
	
	@GetMapping(value="/person")
	@ResponseBody
	public List<Person> getAll() {
		List<Person> persons = new ArrayList<>();
		this.manager.findAll().forEach( p -> persons.add(p));
		return persons;
	}
        
        @GetMapping(value="/person/me")
	@ResponseBody
	public Person getMe(@RequestHeader("Authorization") String authHeader) {
		return manager.getUserLogged(authHeader);
	}
	
	@GetMapping(value="/person/{id}")
	@ResponseBody
	public Person getById(@PathVariable Long id) {
		return this.manager.findById(id);
	}
	
	@GetMapping(value="/person/search/{text}")
	@ResponseBody
	public List<Person> searchPerson(@PathVariable String text) {
		return this.manager.findByCustomText(text);
	}
	
	@PostMapping(value="/person")
	@ResponseBody
	public void create(@RequestBody Person person) {
		this.manager.save(person);
	}

	@PutMapping(value="/person")
	@ResponseBody
	public void UpdatePerson(@RequestHeader("Authorization") String authHeader, @RequestBody Person person) {
		
		String username = AuthToken.getAuthenticatedUser(authHeader);
		Person p = manager.findByUsername(username);

		if (person.getName() != null) p.setName(person.getName());
		if (person.getSurname() != null) p.setSurname(person.getSurname());
		//if (person.getPicture() != null) p.setPicture(person.getPicture());

		// Updateamos		
		manager.update(p);

	}
	/*
	// TODO relate  -> coger id del token cuando este
	@PostMapping(value="/person/{id}/relate")
	@ResponseBody
	public void relate(@PathVariable Long id) {		
		manager.relatePerson(id);
	}
	/*
	// TODO ruta que te devuelva todos los amigos de una persona -> por comprobar
	@GetMapping(value="/person/friends")
	@ResponseBody
	public Iterable<Person> getAllFriends(Long id) {
		Person user = this.manager.findById(id);
		return user.getFriends();
	}
	*/
	@DeleteMapping(value="/person/{id}")
	@ResponseBody
	public void remove(@PathVariable Long id) {
		final Person person = manager.findById(id);
		this.manager.remove(person);
	}

}
