package com.social.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private Date startingDate;
	private Date endingDate;
	private EventType type;
	private byte[] picture;
}