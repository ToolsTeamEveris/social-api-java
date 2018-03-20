package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Person;
import com.social.manager.PersonManager;



@RestController
public class SocialController  {
	
	private final PersonManager manager;

	@Autowired
	public SocialController(final PersonManager personManager) {
		this.manager = personManager;
	}

	@GetMapping("/persons")
	@ResponseBody
	public Iterable<Person> findAll() {
		return this.manager.findAll();
	}
	
	@PostMapping("/persons")
	@ResponseBody
	public void create(@RequestBody Person newPerson) {
		this.manager.save(newPerson);
	}
	

}
