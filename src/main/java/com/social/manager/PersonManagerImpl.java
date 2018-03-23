package com.social.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Person;
import com.social.repository.PersonRepository;

@Service
public final class PersonManagerImpl implements PersonManager {
	
	
	private final PersonRepository personRepository;
	
	@Autowired
	public PersonManagerImpl(final PersonRepository personRepository) {
		this.personRepository = personRepository;
		
	}

	@Override
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}
	
	@Override
	public Person findById(Long id) {
		if (personRepository.findById(id).isPresent())
			return personRepository.findById(id).get();
		else
			return null;
	}
	
	@Override
	public void save(final Person nPerson) {
		this.personRepository.save(nPerson);	
	}

	@Override
	public void save(final Iterable<Person> e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updatePerson(Long id, Person person) {
		Person person2 = findById(id);
		person2.setName(person.getName());
		person2.setSurname(person.getSurname());
		this.personRepository.save(person2);	
	}

	@Override
	public void update(Person e) {
		this.personRepository.save(e);	
	}
	
	

	@Override
	public void update(Iterable<Person> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Person e) {
		personRepository.delete(e);
		
	}

	@Override
	public Person relatePerson(Long id) {
		Long l = (long) 1000;
		List<Person> list = new ArrayList<Person>();
		Person user = findById(l);
		Person person = findById(id);
		
		if(user.getId() != person.getId()) {
			list = user.getFriends();
			list.add(person);
			user.setFriends(list);
			update(user);
			user.setFriends(null);
			return user;
		}
		else {
			return null;
		}
		
		
	}

    @Override
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

}
