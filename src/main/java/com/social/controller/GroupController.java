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
import com.social.manager.GroupManager;
import com.social.manager.PersonManager;

import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GroupController {

	private final GroupManager manager;
        private final PersonManager personManager;
	
	@Autowired
	public GroupController(final GroupManager groupManager, final PersonManager personManager) {
            this.manager = groupManager;
            this.personManager = personManager;
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Group> getAll(){
            return this.manager.findAll();
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Group getById(@PathVariable long id) {
            return this.manager.findById(id);
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.POST)
	@ResponseBody
	public Group create(@RequestBody Group group) {
            
            //TODO get id from token
            Person user = this.personManager.findById(1000l);
            
            group.setCreator(user);
            group.setMembers(new ArrayList<Person>());
            
            this.manager.save(group);
            
            return group;
        }
	
	@RequestMapping(value = "/group/person/{personId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Group> getByPersonId(@PathVariable Long personId) {
            List<Group> groups = new ArrayList<>();
            this.manager.findAll().forEach( g -> groups.add(g));

            //TODO make a custom query
            return groups.stream().filter( g -> g.getCreator().getId().equals(personId)).collect(Collectors.toList());
        }
	
	@RequestMapping(value = "/group/{id}/relate", method = RequestMethod.POST)
	@ResponseBody
	public void getAll(@PathVariable Long id , @RequestBody ArrayList<Long> list){
            Group group = this.manager.findById(id);
            List<Person> persons = new ArrayList<>();
            
            System.out.println(list);

            list.stream()
                    .forEach(userId -> persons.add(this.personManager.findById(userId)));
            
            group.addPerson(persons);
            
            System.out.println(persons);
            System.out.println(group);
            
            this.manager.save(group);
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@PathVariable Long id) {
            Group group = this.manager.findById(id);
            this.manager.remove(group);
	}
	
}
