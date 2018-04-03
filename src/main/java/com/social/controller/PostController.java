package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public List<Post> getAll(@RequestHeader("Authorization") String authHeader) {
		return (List<Post>) this.manager.findAll();
	} 
	
	@RequestMapping(value = "/post/mine", method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getAllMyPost(@RequestHeader("Authorization") String authHeader) {
		return (List<Post>) this.manager.findMyPosts(authHeader);
	}
	
	@RequestMapping(value = "/post/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getFriendPost(@RequestHeader("Authorization") String authHeader) {
		return (List<Post>) this.manager.findFriendsPost(authHeader);
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
	public List<Post> getByReported(@RequestHeader("Authorization") String authHeader){
		return this.manager.findAllByReported(authHeader);
	}
	
	@RequestMapping(value = "/post/reported/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Post reportPost(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
		return this.manager.reportPost(authHeader, id);
	}
	
	@RequestMapping(value = "/post/unreported/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Post undoReport(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
		return this.manager.undoReport(authHeader, id);
	}	
	
	
	@RequestMapping(value = "/post/reported/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteReportedPost(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
		this.manager.deleteReportedPost(authHeader, id);
	}
	
	@RequestMapping(value = "/post/{id}/like", method = RequestMethod.POST)
	@ResponseBody
	public void addLike(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody Like l) {
		this.manager.addLike(id, l, authHeader);
	}
	
	@RequestMapping(value = "/post/{id}/unlike", method = RequestMethod.DELETE)
	@ResponseBody
	public void unlike(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody Like l) {
		this.manager.unlike(id, l, authHeader);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@ResponseBody
	public void create(@RequestHeader("Authorization") String authHeader, @RequestBody Post newPost) {
		this.manager.save(newPost, authHeader);
	}
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@RequestHeader("Authorization") String authHeader, @PathVariable() Long id) {
		this.manager.deleteById(id, authHeader);
	}
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestHeader("Authorization") String authHeader, @RequestBody Post e) {
		this.manager.update(e, authHeader);
	}
}