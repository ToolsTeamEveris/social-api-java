package com.social.manager;

import java.util.List;

import com.social.entity.Like;

public interface LikeManager extends Manager<Like>{
	List<Like> findAllById(Long id);
}
