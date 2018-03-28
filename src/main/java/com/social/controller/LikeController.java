package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Like;
import com.social.entity.Post;
import com.social.manager.LikeManagerImpl;

@RestController
public class LikeController {
	private final LikeManagerImpl manager;
	
	@Autowired
	public LikeController (final LikeManagerImpl likeManager) {
		this.manager = likeManager;
	}
	
	@RequestMapping(value = "/like/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Like> getByPersonId(@PathVariable Long id) {		
		return this.manager.findAllById(id);
	}
	
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody Like newLike) {
		this.manager.save(newLike);
	}
	

}
