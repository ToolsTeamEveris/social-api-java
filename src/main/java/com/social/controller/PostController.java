package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.entity.Like;
import com.social.entity.Post;
import com.social.manager.PostManagerImpl;

@Controller
public class PostController {
	
	private PostManagerImpl manager;
	
	@Autowired
	public PostController(final PostManagerImpl postManager) {
		this.manager = postManager;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getAll() {
		return (List<Post>) this.manager.findAll();
	} 
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Post getById(@PathVariable Long id) {
		return this.manager.findById(id);
	}
	
	@RequestMapping(value = "/post/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getByPersonId(@PathVariable Long id) {		
		return this.manager.findAllById(id);
	}
	
	@RequestMapping(value = "/post/reported", method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getByReported(){
		return this.manager.findAllByReported();
	}
	
	@RequestMapping(value = "/post/{id}/like", method = RequestMethod.POST)
	@ResponseBody
	public void update(@PathVariable Long id, @RequestBody Like l) {
		this.manager.addLike(id, l);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody Post newPost) {
		this.manager.save(newPost);
	}
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@PathVariable() Long id) {
		this.manager.deleteById(id);
	}
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Post e) {
		this.manager.update(e);
	}
}