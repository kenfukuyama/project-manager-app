package com.kb.chitchat.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kb.chitchat.models.Project;
import com.kb.chitchat.models.Task;
import com.kb.chitchat.models.User;
import com.kb.chitchat.services.ProjectService;
import com.kb.chitchat.services.TaskService;
import com.kb.chitchat.services.UserService;


@Controller
public class ProjectController {
	@Autowired 
	private ProjectService projectService;
	
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	
	   
    // ! SHOW  =================================================================
	@GetMapping("/home")
    public String home(HttpSession session, Model model, @ModelAttribute Project project) {
    	Long userId = (Long)session.getAttribute("userId");
    	
    	// put user back if not logged in
    	if (userId == null) {
    		return "redirect:/";
    	} 
    	
    	User user = userService.findUser(userId);
    	model.addAttribute("user", user);
    	
    	 // change here
    	List<Project> projects = projectService.allProjects();
    	model.addAttribute("projects", projects);

    	
    	return "dashboard.jsp";
    }
	
    // ! show 1 project page
    @GetMapping("/projects/{id}")
    public String showProject(@PathVariable("id") Long id, Model model, HttpSession session) {
    	model.addAttribute("project", projectService.findProject(id));
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	
    	// errors
    	
    	return "showProject.jsp";
    	
    }
    

   
	
    
	 // ! Create  =================================================================
    // ! new Project
    @GetMapping("/projects/add")
    public String addProject(@ModelAttribute("project") Project project, Model model,
    		HttpSession session) {
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	
        return "newProject.jsp";
    }
    
    // ! POST new projects
    @PostMapping("/projects/add")
    public String createProject(@Valid @ModelAttribute("project") Project project,
            BindingResult result, Model model, HttpSession session) {
    	
        if (result.hasErrors()) {
        	model.addAttribute("userId", (Long)session.getAttribute("userId"));
            return "newProject.jsp";
        }
       
        projectService.saveProject(project);
        return "redirect:/home";
        
    }
    
    // ! GET new Task
    @GetMapping("/projects/{id}/task")
    public String taskPage(@ModelAttribute("task") Task task, Model model,
    		HttpSession session, @PathVariable("id") Long id) {
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	model.addAttribute("project", projectService.findProject(id));  
    	model.addAttribute("tasks", taskService.AllTasksByProject(id));
    	
        return "showTask.jsp";
    }
    
    // ! POST new comments
    @PostMapping("/tasks/add")
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result) {
    	if (result.hasErrors()) {
    		return "redirect:/projects/" + task.getProject().getId() + "/task";
    	}
    	
    	taskService.saveTask(task);
    	return "redirect:/projects/" + task.getProject().getId() + "/task";
    }

    // ! Add connection
    @PostMapping("/projects/addConnect")
    public String addConnect(@RequestParam("userId") Long userId,
    						 @RequestParam("projectId") Long projectId) {
    	projectService.addConnect(userId, projectId);
    	return "redirect:/home";
    }
    
    
    
    // ! Edit =================================================================
    // ! Edit project page
    @GetMapping("/projects/{id}/edit")
    public String editProject(@PathVariable("id") Long id, Model model, HttpSession session) {
    	// validation for logged in user
    	Long userId = (Long) session.getAttribute("userId");   
    	Long projectUserId = projectService.findProject(id).getUser().getId();
    	
    	if (!userId.equals(projectUserId)) {
    		return "redirect:/home";
    	}
    	
    	model.addAttribute("project", projectService.findProject(id));
    	return "editProject.jsp";

    }
    
    
    // Edit put request
    @RequestMapping(value="/projects/{id}", method=RequestMethod.PUT)
    public String updateProject(@Valid @ModelAttribute("project") Project project, BindingResult result, 
    		@PathVariable("id") Long id) {
    	
    	System.out.println("tring to update");
        if (result.hasErrors()) {
            return  "editProject.jsp";
        } else {
            projectService.saveProject(project);
            return "redirect:/home";
        }
        
        
        
    }
    
    
    // Delete Route ============================================================
    // basic delete
    @RequestMapping(value="/projects/{id}", method=RequestMethod.DELETE)
    public String deleteProject(@PathVariable("id") Long id) {
    	projectService.deleteProject(projectService.findProject(id));
    	return "redirect:/home";
    }
    

    // remove connection
    @PostMapping("/projects/removeConnect")
    public String removeConnect(@RequestParam("userId") Long userId, @RequestParam("projectId") Long projectId) {
    	projectService.removeConnect(userId, projectId);
    	System.out.println("removing");
    	return "redirect:/home";
    }

    
    


}
