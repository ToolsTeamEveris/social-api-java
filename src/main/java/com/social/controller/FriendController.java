package com.social.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.entity.Friend;
import com.social.entity.Post;
import com.social.manager.FriendManagerImpl;

@Controller
public class FriendController {

	private FriendManagerImpl managerImpl;

	public FriendController(final FriendManagerImpl managerImpl) {
		this.managerImpl = managerImpl;
	}
	
	@ResponseBody
	@GetMapping(value="/friend")
	public List<Friend> getAll() {
		return (List<Friend>) this.managerImpl.findAll();
	} 
	
	@GetMapping(value="/friend/{id}")
	@ResponseBody
	public Friend getById(@PathVariable Long id) {
		return this.managerImpl.findById(id);
	}
	
	@PostMapping(value ="/friend/{id}")
	@ResponseBody
	public Friend requestFriend(@PathVariable Long id) {
		return this.managerImpl.saveFriendshipRequest(id);
	}
	
	
	@PutMapping(value="/friend/{id}")
	@ResponseBody
	public Friend confirmFriendship(@PathVariable Long id) {
		return this.managerImpl.confirmFriendShip(id);
	}
	
	@DeleteMapping(value="/friend/{id}")
	@ResponseBody
	public Friend deleteFriendship(@PathVariable Long id) {
		return this.managerImpl.deleteFriendship(id);
	}
	
}
