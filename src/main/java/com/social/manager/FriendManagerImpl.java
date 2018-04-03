package com.social.manager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Friend;
import com.social.entity.FriendPK;
import com.social.entity.Person;
import com.social.repository.FriendRepository;

@Service
public class FriendManagerImpl implements FriendManager<Friend>{

	private final FriendRepository friendRepository;
	private final PersonManagerImpl personManager;
	private Person user_logged;
	Session session;
	
	@PersistenceContext
    private EntityManager em;
	
	CriteriaBuilder cb;
	CriteriaQuery<Friend> criteriaQuery;
	Root<Friend> root;
	
	@Autowired
	public FriendManagerImpl(final FriendRepository friendRepository,
								final PersonManagerImpl personManager) {
		this.friendRepository = friendRepository;
		this.personManager = personManager;
		user_logged  = personManager.findById(1000L);
		
	}

	@Override
	public Iterable<Friend> findAll() {
		cb = em.getCriteriaBuilder();
		criteriaQuery = cb.createQuery(Friend.class);
		root = criteriaQuery.from(Friend.class);
		Friend friend = new Friend();
		friend.setFriendPK(new FriendPK(user_logged,user_logged));
		friend.setAccepted(false);
		Predicate predicate = cb.or(cb.equal(root.get("friendPK").get("user"), user_logged),cb.equal(root.get("friendPK").get("friend"), user_logged));
		criteriaQuery.select(root).where(predicate);
		Query<Friend> query = (Query<Friend>) em.createQuery(criteriaQuery);
		
		return query.getResultList();
		
	}
	
	@Override
	public Friend findById(Long id) {
		Person friend = personManager.findById(id);
		if (friendRepository.findById(new FriendPK(user_logged, friend)).isPresent())
			return friendRepository.findById(new FriendPK(user_logged, friend)).get();
		else
			return null;
	}
	
	@Override
	public Friend confirmFriendShip(Long id) {
		Friend friend = findById(id);
		friend.setAccepted(true);
		save(friend);
		return friend;
	}
	
	@Override
	public Friend saveFriendshipRequest(Long id) {
		Person userFriend = personManager.findById(id);
		Friend friend = new Friend();
		friend.setFriendPK(new FriendPK(user_logged,userFriend));
		friend.setAccepted(false);
		save(friend);
		return friend;
	}
	
	@Override
	public Friend deleteFriendship(Long id) {
		Friend friend = findById(id);
		remove(friend);
		return friend;
	}
	
	@Override
	public void save(Friend e) {
		// TODO Auto-generated method stub
		friendRepository.save(e);
	}
	
	@Override
	public void save(Iterable<Friend> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Friend e) {
		// TODO Auto-generated method stub
		friendRepository.save(e);
	}

	@Override
	public void update(Iterable<Friend> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Friend e) {
		// TODO Auto-generated method stub
		friendRepository.delete(e);
	}

	

	

}
