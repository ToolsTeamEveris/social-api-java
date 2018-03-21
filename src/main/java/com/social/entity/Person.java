package com.social.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	 @ManyToMany
	  @JoinTable(
	      name="friends",
	      joinColumns=@JoinColumn(name="Person", referencedColumnName="id"))
	private List<Person> friends;
	
}
