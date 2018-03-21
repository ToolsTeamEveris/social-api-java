package com.social.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Like;
import com.social.entity.Post;
import com.social.repository.EventRepository;
import com.social.repository.PostRepository;

@Service
public class PostManagerImpl implements PostManager<Post> {
	
	private final PostRepository postRepository;
	
	@Autowired
	public PostManagerImpl(final PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Iterable<Post> findAll() {
		/*List<Post> posts = new ArrayList<>();
		postRepository.findAll().forEach( p -> posts.add(p));
		return posts;*/
		return postRepository.findAll();
	}
	
	public List<Post> findAllById(Long id){
		return this.postRepository.findByCreatorId(id);
	}

	@Override
	public void save(Post event) {
		this.postRepository.save(event);
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
		if (postRepository.findById(id).isPresent())
			return postRepository.findById(id).get();
		else
			return null;
	}
	
	public void deleteById(Long id) {
		this.postRepository.deleteById(id);
	}


}