package com.social.manager;

import com.social.entity.Like;
import com.social.entity.Post;

public interface PostManager<E> extends Manager<E> {
	public Post addLike(Like like);
}
