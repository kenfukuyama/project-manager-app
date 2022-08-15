package com.kb.chitchat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kb.chitchat.models.Task;


@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
	List<Task> findAll();
	
	
	// all comments for specific events
	List<Task> findByProjectId(Long projectId);
	
}