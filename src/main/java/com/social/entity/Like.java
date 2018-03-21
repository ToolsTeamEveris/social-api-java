package com.social.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Like {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name="post")
	private Post post;
	
	@OneToOne
	@JoinColumn(name="from")
	private Person person;
	private Date creationDate;
	private LikeType type;
}
