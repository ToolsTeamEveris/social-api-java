package com.social.manager;

import org.springframework.stereotype.Service;

import com.social.entity.Event;
import com.social.entity.Like;
import com.social.entity.Post;

@Service
public class PostManagerImpl implements PostManager<Post> {
	
	

	@Override
	public Iterable<Post> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Post e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Iterable<Post> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Post e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Iterable<Post> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Post e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post addLike(Like like) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
