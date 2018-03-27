package com.social.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class FriendPK implements Serializable{

	public Person user;
	public Person friend;
	
	public FriendPK(Person user, Person friend) {
		super();
		this.user = user;
		this.friend = friend;
	}

	public FriendPK() {
		
	}
	
}
