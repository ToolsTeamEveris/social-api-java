package com.social.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class FriendPK implements Serializable{
	@ManyToOne
	@JoinColumn(name="sender", referencedColumnName="id")
	public Person sender_user;
	@ManyToOne
	@JoinColumn(name="receiver", referencedColumnName="id")
	public Person receiver_user;
	
	public FriendPK(Person user, Person friend) {
		super();
		this.sender_user = user;
		this.receiver_user = friend;
	}

	public FriendPK() {
		
	}
	
}
