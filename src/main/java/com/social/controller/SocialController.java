package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Person;
import com.social.manager.PersonManager;
import com.social.manager.PersonManagerImpl;



@RestController
public class SocialController  {
	
	private PersonManagerImpl manager = new PersonManagerImpl();

//	public SocialController(final PersonManager personManager) {
//		this.manager = personManager;
//	}
	
	public SocialController() { }
	
	

	@GetMapping("/persons")
	@ResponseBody
	public Iterable<Person> findAll() {
		return this.manager.findAll();
	}
	

}
