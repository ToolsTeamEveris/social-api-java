package com.social.manager;

import com.social.entity.FriendPK;
import java.util.List;
import com.social.entity.Friend;


public interface FriendManager<E> extends Manager<E> {
	Friend saveFriendshipRequest(Long id);
	Friend confirmFriendShip(Long id);
	Friend deleteFriendship(Long id);
	
}
