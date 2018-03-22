package com.social.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="grupo")
public class Group {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
        @ManyToOne
	@JoinColumn(name="creator")
	private Person creator;
	@OneToMany
	@JoinTable(
            name="members",
            joinColumns=@JoinColumn(name="Person", referencedColumnName="id"))
	private List<Person> members;
	private String name;
	//private byte[] picture;
	
        public void addPerson(Person person) {
            this.members.add(person);
        }
        
        public void addPerson(List<Person> persons) {
            persons.stream().forEach(p -> this.members.add(p));
        }
        
}
