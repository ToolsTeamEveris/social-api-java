package com.social.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {
	List<Like> findByCreatorId(Long personId);
	
}
