package com.social.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Friend;
import com.social.entity.FriendPK;
import com.social.entity.Like;
import com.social.entity.Person;
import com.social.entity.PersonType;
import com.social.entity.Post;
import com.social.repository.FriendRepository;
import com.social.repository.PostRepository;

import helper.AuthToken;

@Service
public class PostManagerImpl implements PostManager<Post> {
	
	private final PostRepository postRepository;
	private final PersonManagerImpl personManager;
	private final FriendRepository friendRepository;
	private final FriendManagerImpl friendManager;
	private Person user_logged;
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	public PostManagerImpl(final PostRepository postRepository, PersonManagerImpl personManager, 
			FriendRepository friendRepository, FriendManagerImpl friendManager) {
		this.postRepository = postRepository;
		this.personManager = personManager;
		this.friendRepository = friendRepository;
		this.friendManager = friendManager;
	}
	
	@Override
	public Iterable<Post> findAll() {
		return postRepository.findAll();
	}
	
	@Override
	public List<Post> findAllById(Long id){
		return this.postRepository.findByCreatorId(id);
	}
	
	@Override
	public List<Post> findAllByReported(String username){
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);

		if(user_logged.getType().equals(PersonType.ADMIN)) {
			return this.postRepository.findByReported(true);
		} else {
			return null;
		}
	}
	
	@Override
	public Post reportPost(String authHeader, Long id) {
		if(postRepository.findById(id).isPresent()) {
			Post post = postRepository.findById(id).get();
			post.setReported(true);
			return postRepository.save(post);
		} else {
			return null;
		}
	}
	
	@Override
	public Post undoReport(String username, Long id) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		if(user_logged.getType().equals(PersonType.ADMIN)) {
			if(postRepository.findById(id).isPresent()) {
				Post post = postRepository.findById(id).get();
				post.setReported(false);
				return postRepository.save(post);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void deleteReportedPost(String username, Long id) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		if(user_logged.getType().equals(PersonType.ADMIN)) {
			if(postRepository.findById(id).isPresent()) {
				postRepository.delete(postRepository.findById(id).get());
			}		
		}
	}

	@Override
	public void save(Post post, String username) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		post.setCreator(user_logged);
		post.setCreationDate(new Date());

		this.postRepository.save(post);
	}

	@Override
	public void save(Iterable<Post> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Post e, String username) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		if(e.getCreator().getId().equals(user_logged.getId())) {
			postRepository.save(e);
		}
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
	public Post findById(Long id) {
		if (postRepository.findById(id).isPresent())
			return postRepository.findById(id).get();
		else
			return null;
	}
	
	@Override
	public void deleteById(Long id, String username) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		if(postRepository.findById(id).isPresent()) {
			Post post = postRepository.findById(id).get();
			
			if(post.getCreator().getId().equals(user_logged.getId())) {
				postRepository.delete(post);
			}
		}
	}

	@Override
	public List<Post> findFriendsPost(String username) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		List<Post> posts = new ArrayList<Post>();

		for(Friend f : friendManager.getRelatedPersons(username)){
			Person friend = personManager.findById(f.getFriendPK().getReceiver_user().getId());
			
			posts.addAll(postRepository.findByCreatorId(friend.getId()));
		}
		
		posts.addAll(postRepository.findByCreatorId(user_logged.getId()));
		
		return posts;		
	}

	@Override
	public List<Post> findMyPosts(String username) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		List<Post> myPosts = postRepository.findByCreatorId(user_logged.getId());
		
		Collections.reverse(myPosts);

		return myPosts;
	}

	@Override
	public void save(Post e) {}
	
	@Override
	public Post addLike(Long id, Like like, String username) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		if(postRepository.findById(id).isPresent()) {
			Post post = postRepository.findById(id).get();
			
			like.setCreator(user_logged);
			like.setCreation_date(new Date());
			post.getLikes().add(like);
			
			return postRepository.save(post);
		} else {
			return null;
		}	
	}
	
	@Override
	public Post unlike(Long id, Like like, String username) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		if(postRepository.findById(id).isPresent()) {
			
			Post post = postRepository.findById(id).get();
			System.out.println(post.toString());
			
			for(Like l : post.getLikes()) {
				if(l.getId().equals(like.getId())) {
					post.getLikes().remove(l);
					
					return postRepository.save(post);
				}
			}	
			
			return null;
		} else {
			return null;
		}	
	}

	@Override
	public void update(Post e) {
		// TODO Auto-generated method stub
		
	}
}