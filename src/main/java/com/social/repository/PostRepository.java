package com.social.repository;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

}
