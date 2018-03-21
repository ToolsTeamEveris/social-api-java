package com.social.manager;

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
	public void update(Person e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Iterable<Person> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Person e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person relatePerson(Iterable<Person> person) {
		// TODO Auto-generated method stub
		return null;
	}

}
