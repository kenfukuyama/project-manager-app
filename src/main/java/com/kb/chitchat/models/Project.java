package com.kb.chitchat.models;

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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

// Domain Model
@Entity // Related to databases
@Table(name="projects") // TODO: change the table name
public class Project {
	// domain models allow us to create table
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;
	
    @NotNull
    @Size(min = 3, max = 200, message="name needs to be more than 3 characters")
    private String name;
    
    
    @NotNull
    @Future
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date projectDate;
    
    @NotNull
    @Size(min = 3, max = 200, message="description needs to be more than 3 characters")
    private String description;
    
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
	// before it created
    @PrePersist 
    protected void onCreate(){
        this.createdAt = new Date();
    }
    // before it updates, null at first
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
    
    // TODO: Add relationship
    // TODO: getter for setter for relationships too.
    // many to one relationship
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    // you could use thingID, but it will convert to thing_id in MySQL databases
    // TODO: Add relationship - many to many
    // TODO: getter for setter for relationships too.
    // many to one relationship

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="memberships",
    		joinColumns = @JoinColumn(name = "project_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="tasks",
    		joinColumns = @JoinColumn(name = "project_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> taskingUsers;

	
	// empty public constructor
    public Project() {}

    // TODO: overloaded constructor
	public Project(String name, Date evenDate, String description) {
		this.name = name;
		this.projectDate = evenDate;
		this.description = description;
	}
	
    // TODO add getter and setters for member variable
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	
	public List<User> getTaskingUsers() {
		return taskingUsers;
	}
	public void setTaskingUsers(List<User> taskingUsers) {
		this.taskingUsers = taskingUsers;
	}


}
