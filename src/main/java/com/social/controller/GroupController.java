package com.social.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Group;
import com.social.entity.Person;
import com.social.manager.GroupManagerImpl;

@RestController
public class GroupController {

	private GroupManagerImpl manager = new GroupManagerImpl();
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	@ResponseBody
	public List<Group> getAll(){
		List<Group> groupList = new ArrayList<Group>();
		return groupList;
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Group getById(@PathVariable long id) {
		return null;
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.POST)
	@ResponseBody
	public Group create(Group group) {
		return null;
	}
	
	@RequestMapping(value = "/group/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Group getByPersonId(@PathVariable Long personId) {
		return null;
	}
	
	@RequestMapping(value = "/group/{id}/relate", method = RequestMethod.POST)
	@ResponseBody
	public Person getAll(@PathVariable String id , List<String> list){
	
		return null;
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@PathVariable Long id) {
		
	}
	
}
