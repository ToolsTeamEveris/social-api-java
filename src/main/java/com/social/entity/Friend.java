package com.social.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="friend_relation")
public class Friend {
	
	@EmbeddedId
	private FriendPK friendPK;
	private Boolean accepted;
	
}
