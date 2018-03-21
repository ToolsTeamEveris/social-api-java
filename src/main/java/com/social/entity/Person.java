package com.social.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD

import lombok.Data;

=======
import javax.persistence.SequenceGenerator;
>>>>>>> 4f2f27542d5fa75bb55ce7716482da987ee96d25

@Entity
@Data
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	private List<Person> friends;
	
<<<<<<< HEAD
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getSurname() {
//		return surname;
//	}
//	public void setSurname(String surname) {
//		this.surname = surname;
//	}
=======
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<Person> getFriends() {
		return friends;
	}
	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	public Long getId() {
		return id;
	}
>>>>>>> 4f2f27542d5fa75bb55ce7716482da987ee96d25
	
}
