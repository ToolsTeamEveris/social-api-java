package com.social.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="creator")
	private Person creator;
	@OneToMany
	@JoinTable(
		      name="assistants",
		      joinColumns=@JoinColumn(name="Person", referencedColumnName="id"))
	private List<Person> assistants;
	private String name;
	private Date startingDate;
	private Date endingDate;
	private EventType type;
	@Column(columnDefinition="TEXT")
	private String picture;
	
}
