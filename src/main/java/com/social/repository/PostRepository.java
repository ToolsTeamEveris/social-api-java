package com.social.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Post;
import com.social.manager.Manager;

public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findByCreatorId(Long personId);
	List<Post> findByReported(boolean reported);
	
}
