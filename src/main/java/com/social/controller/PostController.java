package com.social.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.manager.PostManager;
import com.social.entity.Post;

@Controller
public class PostController {
	private PostManager<Post> manager;
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getAll() {
		return new ArrayList<Post>();
	} 
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Post getById(@PathVariable() Long id) {
		return new Post();
	}
	
	@RequestMapping(value = "/post/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Post getByPersonId(@PathVariable() Long id) {
		return new Post();
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@ResponseBody
	public Post create(@RequestBody Post newPost) {
		return new Post();
	}
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@PathVariable() Long id) {
		
	}
}