package com.social.manager;

import java.util.List;

import com.social.entity.Like;
import com.social.entity.Post;

public interface PostManager<E> extends Manager<E> {
	List<Post> findFriendsPost(String username);
	List<Post> findMyPosts(String username);
	void save(Post event, String username);
	Post addLike(Long id, Like like, String authHeader);
	Post unlike(Long id, Like like, String authHeader);
	void update(Post e, String username);
}
