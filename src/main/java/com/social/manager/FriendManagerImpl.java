package com.social.manager;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.plaf.multi.MultiSeparatorUI;

import org.hibernate.query.Query;
import org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Friend;
import com.social.entity.FriendPK;
import com.social.entity.Person;
import com.social.repository.FriendRepository;

import helper.AuthToken;

@Service
public class FriendManagerImpl implements FriendManager<Friend>{

	private final FriendRepository friendRepository;
	private final PersonManagerImpl personManager;
	private Person user_logged;
	
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
	}
	
	@Override
	public List<Friend> suggestedFriends(String authHeader, int limit) {
		//Get the logged user
		String user_logged_str = AuthToken.getAuthenticatedUser(authHeader);
		user_logged  = personManager.findByUsername(user_logged_str);
		List<Friend> friends = getRelatedPersons(authHeader);
		List<Friend> friendsOfFriends = new ArrayList<>();
		friends.forEach((f) -> {
			String user = ""; 
			if(f.getFriendPK().getSender_user().getUsername().equals(user_logged_str)) {
				user = f.getFriendPK().getReceiver_user().getUsername();
			}else {
				user = f.getFriendPK().getSender_user().getUsername();
			}
			List<Friend> fList = getRelatedPersons(user);
			fList.forEach((ff) -> friendsOfFriends.add(ff));
		});
		
		Map<Friend, Long> friendMap =
			    friendsOfFriends
			    	.stream()
			    	.filter(ff -> !friends.contains(ff))
			    	.collect(Collectors.groupingBy( e -> e, Collectors.counting()))
			    	.entrySet()
			        .stream()
			        .sorted(Map.Entry.<Friend, Long>comparingByValue(Comparator.reverseOrder()))
			        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {throw new IllegalStateException();}, LinkedHashMap::new));
		friendsOfFriends.clear();
		
		for (Map.Entry<Friend, Long> entry : friendMap.entrySet()) {
			if(friendsOfFriends.size() < limit) {
				friendsOfFriends.add(entry.getKey());
			}
		}
		
		return friendsOfFriends;
	}

	@Override
	public List<Friend> getRelatedPersons(String username) {
		//Get the logged user
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		//Create the Query
		cb = em.getCriteriaBuilder();
		criteriaQuery = cb.createQuery(Friend.class);
		root = criteriaQuery.from(Friend.class);
		Predicate predicate = cb.or(cb.equal(root.get("friendPK").get("sender_user"), user_logged),cb.equal(root.get("friendPK").get("receiver_user"), user_logged));
		criteriaQuery.select(root).where(predicate);
		//Make the Query
		Query<Friend> query = (Query<Friend>) em.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
	@Override
	public Friend getFriend(String username,Long id) {
		//Get the user logged
		Person friend = personManager.findById(id);
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		//Find the user
		if (friendRepository.findById(new FriendPK(user_logged, friend)).isPresent())
			return friendRepository.findById(new FriendPK(user_logged, friend)).get();
		else
			return null;
	}
	
	
	@Override
	public Friend confirmFriendShip(String username,Long id) {

		Person receiver = personManager.findById(id);
		Friend friend = new Friend();
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);

		if (friendRepository.findById(new FriendPK(receiver, user_logged)).isPresent()) {
			friend =  friendRepository.findById(new FriendPK(receiver, user_logged)).get();
			friend.setAccepted(true);
			save(friend);
		}else {
			friend = null;
		}
		
		return friend;
	}
	
	@Override
	public Friend saveFriendshipRequest(String username,Long id) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		Person userFriend = personManager.findById(id);
		Friend friend = new Friend();
		
		if(!friendRepository.findById(new FriendPK(userFriend, user_logged)).isPresent()) {
			friend.setFriendPK(new FriendPK(user_logged,userFriend));
			friend.setAccepted(false);
			save(friend);
		}else {
			friend = null;
		}
		
		return friend;
	}
	
	
	@Override
	public Friend deleteFriendship(String username,Long id) {
		String user_logged_str = AuthToken.getAuthenticatedUser(username);
		user_logged  = personManager.findByUsername(user_logged_str);
		
		Person receiver = personManager.findById(id);
		Friend friend = new Friend();
		
		if (friendRepository.findById(new FriendPK(receiver, user_logged)).isPresent()) {
			friend =  friendRepository.findById(new FriendPK(receiver, user_logged)).get();
			remove(friend);
		}else if (friendRepository.findById(new FriendPK(user_logged, receiver)).isPresent()){
			friend =  friendRepository.findById(new FriendPK(user_logged, receiver)).get();
			remove(friend);
		}else {
			friend = null;
		}
		
		return friend;
	}
	
	@Override
	public Iterable<Friend> findAll() {
		return friendRepository.findAll();
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

	@Override
	public Friend findById(Long id) {
		return null;
	}

	

}
