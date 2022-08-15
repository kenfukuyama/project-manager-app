package com.kb.chitchat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kb.chitchat.models.Project;


@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {
	List<Project> findAll();
	
}