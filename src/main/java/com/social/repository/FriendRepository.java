package com.social.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.Friend;
import com.social.entity.FriendPK;

public interface FriendRepository extends CrudRepository<Friend, FriendPK> {
	public List<Friend> findByAccepted(Boolean accepted);
}
