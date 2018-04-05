package com.social.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Entity
@Data
public class Post implements Comparable<Post>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="creator")
	private Person creator;
	private String text;
	private Date creationDate;
	private Float lat;
	private Float lng;
	private PostType type;
	private boolean reported;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="likes")
	private List<Like> likes;
	@Column(columnDefinition="TEXT")
	private String picture;
	
	@Override
	public int compareTo(Post post) {
		return this.creationDate.compareTo(post.creationDate);
	}
	
}
