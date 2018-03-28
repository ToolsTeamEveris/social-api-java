package com.social.manager;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Like;
import com.social.entity.Post;
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
	
	public List<Post> findAllByReported(){
		return this.postRepository.findByReported(true);
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
		this.postRepository.save(e);	
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
	public Post addLike(Long id, Like like) {
		//Guardar el like antes de asociarlo al post en la BD
		if(postRepository.findById(id).isPresent()) {
			Post post = postRepository.findById(id).get();
			
			//like.setCreator();
			like.setCreation_date(new Date());
			
			post.getLikes().add(like);
			
			return postRepository.save(post);
		} else {
			return null;
		}
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