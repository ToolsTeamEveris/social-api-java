package com.social.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Group;
import com.social.entity.Person;
import com.social.manager.GroupManagerImpl;
import com.social.manager.PersonManagerImpl;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GroupController {

	private GroupManagerImpl manager;
        private PersonManagerImpl personManager;
	
	@Autowired
	public GroupController(final GroupManagerImpl groupManager) {
            this.manager = groupManager;
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	@ResponseBody
	public List<Group> getAll(){
            List<Group> list = new ArrayList<Group>();
            this.manager.findAll().forEach( g -> list.add(g));
            return list;
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Group getById(@PathVariable long id) {
            return this.manager.findById(id);
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.POST)
	@ResponseBody
	public Group create(@RequestBody Group group) {
            this.manager.save(group);
            return group;
        }
	
	@RequestMapping(value = "/group/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Group getByPersonId(@PathVariable Long personId) {
            List<Group> groups = new ArrayList<>();
            this.manager.findAll().forEach( g -> groups.add(g));
            //TODO implement creator at group
            
            return null;
        }
	
	@RequestMapping(value = "/group/{id}/relate", method = RequestMethod.POST)
	@ResponseBody
	public void getAll(@PathVariable Long id , List<Long> list){
            Group group = this.manager.findById(id);
            
            list.stream().map( userId -> {
                Person person = this.personManager.findById(userId);
                group.addPerson(person);
                return person;
            });
            
            this.manager.save(group);
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@PathVariable Long id) {
            Group group = this.manager.findById(id);
            this.manager.remove(group);
	}
	
}
