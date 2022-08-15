package com.kb.chitchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kb.chitchat.models.Task;
import com.kb.chitchat.repositories.TaskRepo;

@Service
public class TaskService {
	@Autowired
	private TaskRepo taskRepo;

	// for many to many
//	@Autowired
//	private UserService userService;

	// read all
	public List<Task> allTasks() {
		return taskRepo.findAll();
	}

	// read one
//	public List<Task> allTasksOfTask(Task task) {
//		return taskRepo.findByTasks(task);
//	}
//	
//	public List<Task> allTasksNotofTask(Task task) {
//		return taskRepo.findByTasksNotContains(task);
//	}

	// Create and Update
	public Task saveTask(Task task) {
		return taskRepo.save(task);
	}

	// delete
	public void deleteTask(Task task) {
		taskRepo.delete(task);
	}

	// read one
	public Task findTask(Long id) {
		Optional<Task> optionalTask = taskRepo.findById(id);

		if (optionalTask.isPresent()) {
			return optionalTask.get();
		} else {
			return null;
		}
	}

	public List<Task> AllTasksByProject(Long projectId) {
		return taskRepo.findByProjectId(projectId);
	}

	// TODO: many to many relationship service
//	public Task addConnect(Long userId, Long taskId) {
//		// retrieve an instance of a task using another method in the service.
//	    Task thisTask = findTask(taskId);
//	    User thisUser = userService.findUser(userId);
//	    // add the user to this task's list of users
//	    thisTask.getAttendingUsers().add(thisUser);
//	    
//	    // Save thisTask, since you made changes to its user list.
//	    return taskRepo.save(thisTask);
//		
//	}
//	

//	public Task removeConnect(Long userId, Long taskId) {
//		// retrieve an instance of a task using another method in the service.
//	    Task thisTask = findTask(taskId);
//	    User thisUser = userService.findUser(userId);
//	    // add the user to this task's list of users
//	    thisTask.getAttendingUsers().remove(thisUser);
//	    
//	    // Save thisTask, since you made changes to its user list.
//	    // TODO: don't forget this!! 
//	    return taskRepo.save(thisTask);
//		
//	}

	// ! TODO addition validation
//	public void checkDate(Task task, BindingResult result) {
//		System.out.println(task.getTaskDate());
//		System.out.println(new Date());
//		
//		if (task.getTaskDate().after(new Date())) {
//			System.out.println("it is future");
//		}
//		else {
//			System.out.println("not futre");
//		}
//	}
//	

}
