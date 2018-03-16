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
	
	
	
}
