package com.social.repository;

import org.springframework.data.repository.CrudRepository;
import com.social.entity.UserPreferences;

public interface UserPreferencesRepository extends CrudRepository<UserPreferences, Long> {

}
