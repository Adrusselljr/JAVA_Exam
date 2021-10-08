package com.russell.beltexam.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ideas")
public class Idea {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "Must Enter A Idea")
    @Size(min = 1, max = 200)
    private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private User creator;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "userIdeaLikes", 
        joinColumns = @JoinColumn(name = "idea_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likes;
	
	// This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Idea() {
    }
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
	

}
