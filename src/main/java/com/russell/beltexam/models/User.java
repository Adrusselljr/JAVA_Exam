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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "Must Enter A First Name")
    @Size(min = 1, max = 200)
    private String firstName;
    
    @NotEmpty(message = "Must Enter A Last Name")
    @Size(min = 1, max = 200)
    private String lastName;
	
	@NotEmpty(message="Email is required!")
	@Email(message="Email must be valid!")
    private String email;
	
	@NotEmpty(message="Password is required!")
	@Size(min=8, message="Password must be greater than 5 characters!")
    private String password;
	
	@Transient
	@NotEmpty(message="Confirm Password is required!")
    private String passwordConfirmation;
	
	@OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    private List<Idea> ideasCreated;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "userIdealikes", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "idea_id")
    )
    private List<Idea> ideasLiked;
	
	// This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public User() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Idea> getIdeasLiked() {
		return ideasLiked;
	}

	public void setIdeasLiked(List<Idea> ideasLiked) {
		this.ideasLiked = ideasLiked;
	}
	
	public List<Idea> getIdeasCreated() {
		return ideasCreated;
	}

	public void setIdeasCreated(List<Idea> ideasCreated) {
		this.ideasCreated = ideasCreated;
	}

}
