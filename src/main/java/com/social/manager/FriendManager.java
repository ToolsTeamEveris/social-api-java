package com.social.manager;

import com.social.entity.FriendPK;
import java.util.List;
import com.social.entity.Friend;


public interface FriendManager<E> extends Manager<E> {
	Friend saveFriendshipRequest(String username,Long id);
	Friend confirmFriendShip(String username,Long id);
	Friend deleteFriendship(String username,Long id);
	List<Friend> getRelatedPersons(String username);
	Friend getFriend(String username,Long id);
}
