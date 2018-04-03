package com.social.manager;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.entity.Friend;
import com.social.entity.FriendPK;
import com.social.entity.Person;
import com.social.entity.UserPreferences;
import com.social.repository.PersonRepository;
import helper.AuthToken;
import helper.DefaultValues;

@Service
public final class PersonManagerImpl implements PersonManager {
	
	
	private final PersonRepository personRepository;
	@PersistenceContext
    private EntityManager em;
	
	CriteriaBuilder cb;
	CriteriaQuery<Person> criteriaQuery;
	Root<Person> root;
	
	@Autowired
	public PersonManagerImpl(final PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Person saveAsDefault(Person person) {
		UserPreferences userPreferences = new UserPreferences();
		userPreferences.setBackgroundColor( DefaultValues.DEFAULT_COLOR);
		userPreferences.setBackgroundImage(DefaultValues.DEFAULT_BACKGROUND);
		userPreferences.setFontStyle(DefaultValues.DEFAULT_FONT);
		person.setUserPreferences(userPreferences);
		save(person);
		return person;
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
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

	@Override
	public List<Person> findByCustomText(String text) {
		
		cb = em.getCriteriaBuilder();
		criteriaQuery = cb.createQuery(Person.class);
		root = criteriaQuery.from(Person.class);
		
		Expression<String> path = root.get("username");
		path = cb.upper(path);
		Expression<String> pathName = root.get("name");
		pathName = cb.upper(pathName);
		Expression<String> pathSurname = root.get("surname");
		pathSurname = cb.upper(pathSurname);
		Predicate predicate = cb.or(
				cb.like(path, "%"+text.toUpperCase()+"%"), 
				cb.like(pathName, "%"+text.toUpperCase()+"%"),
				cb.like(pathSurname, "%"+text.toUpperCase()+"%")
				);
		criteriaQuery.select(root).where(predicate);
		Query<Person> query = (Query<Person>) em.createQuery(criteriaQuery);
		
		return query.getResultList();
	}

    @Override
    public Person getUserLogged(String authHeader) {
        String userName = AuthToken.getAuthenticatedUser(authHeader);
        return findByUsername(userName);
    }

	

}
