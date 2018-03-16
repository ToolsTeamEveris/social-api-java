package com.social.dao;

import com.social.entity.Like;
import com.social.entity.Post;

public interface PostDAO extends EntityDAO<Post>{
	public Post addLike(Like like);
}
