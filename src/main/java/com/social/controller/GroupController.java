package com.social.controller;
import java.util.ArrayList;
import java.util.List;
import com.social.manager.*;
import com.social.entity.*;


public class GroupController {

	GroupManagerImpl manager = new GroupManagerImpl();
	
	public List<Group> getAll(){
		List<Group> groupList = new ArrayList<Group>();
		return groupList;
	}
	
	public Group getById(Long id) {
		return null;
	}
	
	public Group create(Group group) {
		return null;
	}
	
	public Group getByPersonId(Long personId) {
		return null;
	}
	
	public Group remove(Person person) {
		return null;
	}
	
	
}
