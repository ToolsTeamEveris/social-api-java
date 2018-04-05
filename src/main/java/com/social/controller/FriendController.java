package com.social.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.entity.Friend;
import com.social.entity.Person;
import com.social.entity.Post;
import com.social.manager.FriendManagerImpl;

import helper.AuthToken;

@Controller
public class FriendController {

	private FriendManagerImpl managerImpl;

	public FriendController(final FriendManagerImpl managerImpl) {
		this.managerImpl = managerImpl;
	}
	
	@ResponseBody
	@GetMapping(value="/friend/suggested/{limit}")
	public List<Friend> getSuggested(@RequestHeader("Authorization") String authHeader,@PathVariable int limit) {
		return (List<Friend>) this.managerImpl.suggestedFriends(authHeader, limit);
	}
	
	@ResponseBody
	@GetMapping(value="/friend")
	public List<Friend> getAll(@RequestHeader("Authorization") String authHeader) {
		return (List<Friend>) this.managerImpl.getRelatedPersons(authHeader);
	} 
	
	@GetMapping(value="/friend/{id}")
	@ResponseBody
	public Friend getById(@RequestHeader("Authorization") String authHeader,@PathVariable Long id) {
		return this.managerImpl.getFriend(authHeader, id);
	}
	
	@PostMapping(value ="/friend/{id}")
	@ResponseBody
	public Friend requestFriend(@RequestHeader("Authorization") String authHeader,@PathVariable Long id) {
		System.out.println(authHeader);
		return this.managerImpl.saveFriendshipRequest(authHeader, id);
	}
	
	
	@PutMapping(value="/friend/{id}")
	@ResponseBody
	public Friend confirmFriendship(@RequestHeader("Authorization") String authHeader,@PathVariable Long id) {
		return this.managerImpl.confirmFriendShip(authHeader, id);
	}
	
	@DeleteMapping(value="/friend/{id}")
	@ResponseBody
	public Friend deleteFriendship(@RequestHeader("Authorization") String authHeader,@PathVariable Long id) {
		return this.managerImpl.deleteFriendship(authHeader, id);
	}
	
}
