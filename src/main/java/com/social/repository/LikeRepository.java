package com.social.repository;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {

}
