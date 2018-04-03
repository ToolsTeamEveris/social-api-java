/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.social.manager;

import com.social.entity.Person;
import com.social.entity.UserPreferences;
import com.social.repository.PersonRepository;

import helper.DefaultValues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author iguijarm
 */
@Service
public class AuthManager {
    private final PersonRepository personRepository;
    
    @Autowired
    public AuthManager(final PersonRepository personRepository) {
            this.personRepository = personRepository;
    }
    
    public void register(Person user) {
        //Create a new password encoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserPreferences userPreferences = new UserPreferences();
		userPreferences.setBackgroundColor( DefaultValues.DEFAULT_COLOR);
		userPreferences.setBackgroundImage(DefaultValues.DEFAULT_BACKGROUND);
		userPreferences.setFontStyle(DefaultValues.DEFAULT_FONT);
		user.setUserPreferences(userPreferences);
		
        //Encode the password
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        
        user.setPassword(hashedPassword);

        personRepository.save(user);
    }
}
