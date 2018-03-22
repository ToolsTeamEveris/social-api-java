package com.social.manager;

import org.springframework.stereotype.Service;

import com.social.entity.Group;
import com.social.entity.Person;
import com.social.repository.GroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GroupManagerImpl implements GroupManager {
    
    private final GroupRepository groupRepository;

    @Autowired
	public GroupManagerImpl(final GroupRepository groupRepository) {
            this.groupRepository = groupRepository;
	}
            
	@Override
	public Iterable<Group> findAll() {
            return this.groupRepository.findAll();
	}

	@Override
	public void save(Group e) {
            this.groupRepository.save(e);
	}

	@Override
	public void save(Iterable<Group> e) {
            e.forEach( i -> this.groupRepository.save(i));
	}

	@Override
	public void update(Group e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Iterable<Group> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Group e) {
            this.groupRepository.delete(e);
	}

	@Override
	public Group addPersons(Iterable<Person> person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group findById(Long id) {
            Group group = this.groupRepository.findById(id).isPresent() ? 
                                this.groupRepository.findById(id).get() : null;
            return group;            
	}


	
}
