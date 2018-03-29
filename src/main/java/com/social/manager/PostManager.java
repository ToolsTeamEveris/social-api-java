package com.social.manager;

import java.util.List;

import com.social.entity.Like;
import com.social.entity.Post;

public interface PostManager<E> extends Manager<E> {
	List<Post> findFriendsPost(String username);
	List<Post> findMyPosts(String username);
	List<Post> findAllByReported(String username);
	List<Post> findAllById(Long id);
	void save(Post event, String username);
	void update(Post e, String username);
	void deleteById(Long id, String username);
	Post addLike(Long id, Like like, String authHeader);
	Post unlike(Long id, Like like, String authHeader);
	Post undoReport(String authHeader, Long id);
	void deleteReportedPost(String authHeader, Long id);
}
