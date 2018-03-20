package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	

}
