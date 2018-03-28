package com.social.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class FriendPK implements Serializable{

	@Column(length=100000)
	public Person sender_user;
	@Column(length=100000)
	public Person receiver_user;
	
	public FriendPK(Person user, Person friend) {
		super();
		this.sender_user = user;
		this.receiver_user = friend;
	}

	public FriendPK() {
		
	}
	
}
