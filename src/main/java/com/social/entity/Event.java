package com.social.entity;

import java.util.Date;
import java.util.List;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
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
//	private byte[] picture;
	
	public void addAssistant(Person assitant) {
		this.assistants.add(assitant);
		System.out.println("Assistants: " + assistants);
	}
	
	public void deleteAssistant(Person assistant) {
		if (this.assistants.contains(assistant)) {
			this.assistants.remove(assistant);
		}
	}
	
	public void deleteAllAssistants() {
		this.assistants.removeAll(assistants);
	}
	
}
