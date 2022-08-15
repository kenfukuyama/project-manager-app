package com.kb.chitchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kb.chitchat.models.Project;
import com.kb.chitchat.models.User;
import com.kb.chitchat.repositories.ProjectRepo;

@Service
public class ProjectService {
	@Autowired 
	private ProjectRepo projectRepo;

	// for many to many
	@Autowired
	private UserService userService;
	
		
	// read all
	public List<Project> allProjects(){ 
		return projectRepo.findAll();
	}
	
	
	// Create and Update
	public Project saveProject(Project project) {
		return projectRepo.save(project);
	}
	
	// delete
	public void deleteProject(Project project) {
		projectRepo.delete(project);
	}
	
	// read one
	public Project findProject(Long id) {
		Optional<Project> optionalProject = projectRepo.findById(id);
		
		if (optionalProject.isPresent()) { return optionalProject.get(); } 
		else { return null;}
	}
	
	
	
	// TODO: many to many relationship service
	public Project addConnect(Long userId, Long projectId) {
		// retrieve an instance of a project using another method in the service.
	    Project thisProject = findProject(projectId);
	    User thisUser = userService.findUser(userId);
	    // add the user to this project's list of users
	    thisProject.getMembers().add(thisUser);
	    
	    // Save thisProject, since you made changes to its user list.
	    return projectRepo.save(thisProject);
		
	}
	
	
	public Project removeConnect(Long userId, Long projectId) {
		// retrieve an instance of a project using another method in the service.
	    Project thisProject = findProject(projectId);
	    User thisUser = userService.findUser(userId);
	    // add the user to this project's list of users
	    thisProject.getMembers().remove(thisUser);
	    
	    
	    // Save thisProject, since you made changes to its user list.
	    // TODO: don't forget this!! 
	    return projectRepo.save(thisProject);
		
	}
	
	
	// ! TODO addition validation
//	public void checkDate(Project project, BindingResult result) {
//		System.out.println(project.getProjectDate());
//		System.out.println(new Date());
//		
//		if (project.getProjectDate().after(new Date())) {
//			System.out.println("it is future");
//		}
//		else {
//			System.out.println("not futre");
//		}
//	}
//	
	
}
