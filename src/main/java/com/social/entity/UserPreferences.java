package com.social.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="user_preferences")
public class UserPreferences {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String backgroundColor;
	private String backgroundImage;
	private String fontStyle;
}
