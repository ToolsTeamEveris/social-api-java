package com.social.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Like;
import com.social.entity.Post;
import com.social.repository.LikeRepository;
import com.social.repository.PostRepository;

@Service
public final class LikeManagerImpl implements LikeManager {

	private final LikeRepository likeRepository;
	
	@Autowired
	public LikeManagerImpl(final LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	@Override
	public Iterable<Like> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Like findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Like e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Iterable<Like> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Like e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Iterable<Like> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Like e) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Like> findAllById(Long id){
		return this.likeRepository.findByCreatorId(id);
	}

}