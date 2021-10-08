package com.russell.beltexam.contollers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
import com.russell.beltexam.models.Idea;
import com.russell.beltexam.models.User;
import com.russell.beltexam.models.UserIdeaLikes;
import com.russell.beltexam.services.UserService;
import com.russell.beltexam.validations.UserValidator;

@Controller
public class UsersController {
	
	private final UserService userService;
	private final UserValidator userValidator;
    
    public UsersController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("")
    public String home() {
    	return "home.jsp";
    }
    
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    @RequestMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
        if(result.hasErrors()) {
        	return "registrationPage.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("userId", u.getId());
        return "redirect:/ideas";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if(isAuthenticated) {
        	User u = userService.findByEmail(email);
        	session.setAttribute("userId", u.getId());
        	return "redirect:/ideas";
        }
        else {
        	model.addAttribute("error", "Invalid Credentials.  Please try again.");
        	return "loginPage.jsp";
        }
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
////////////////////BELT STARTS HERE ////////////////////
    
    @RequestMapping("/ideas")
    public String home(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        User loggedInUser = userService.findUserById(userId);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("allIdeas", userService.findAllIdeas());
        return "homePage.jsp";
    }
    
    @GetMapping("/ideas/new")
    public String newIdea(@ModelAttribute("idea") Idea idea, Model model) {
    	model.addAttribute("allUsers", this.userService.findAllUsers());
    	return "newIdea.jsp";
    }
    
    @PostMapping("/ideas/create")
    public String createIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, Model model, HttpSession session) {
    	if (result.hasErrors()) {
    		model.addAttribute("allUsers", this.userService.findAllUsers());
            return "newIdea.jsp";
        }
    	else {
    		Long userId = (Long) session.getAttribute("userId");
        	User loggedInUser = userService.findUserById(userId);
        	idea.setCreator(loggedInUser);
            this.userService.createIdea(idea);
            return "redirect:/ideas";
        }
    }
    
    @GetMapping(value="/ideas/{id}")
    public String showIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
    	Long loggedInUserId = (Long) session.getAttribute("userId");
    	User loggedInUser = userService.findUserById(loggedInUserId);
    	model.addAttribute("loggedInUser", loggedInUser);
    	model.addAttribute("ideaToShow", this.userService.findAnIdea(id));
    	return "showIdea.jsp";
    }
    
    @GetMapping("/edit/{id}")
    public String editIdea(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("idea", this.userService.findAnIdea(id));
    	model.addAttribute("allUsers", this.userService.findAllUsers());
    	return "editIdea.jsp";
    }
    
    @PostMapping("/idea/update/{id}")
    public String updateIdea(@PathVariable("id") Long id, @Valid @ModelAttribute("idea") Idea idea, BindingResult result, Model model, HttpSession session) {
    	if (result.hasErrors()) {
    		model.addAttribute("allUsers", this.userService.findAllUsers());
    		return "editIdea.jsp";
    	}
		Idea i = this.userService.findAnIdea(id);
		idea.setLikes(i.getLikes());
		Long loggedInUserId = (Long) session.getAttribute("userId");
    	User loggedInUser = userService.findUserById(loggedInUserId);
    	idea.setCreator(loggedInUser);
    	this.userService.updateIdea(idea);
    	return "redirect:/ideas";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteIdea(@PathVariable("id") Long id) {
    	Idea idea = this.userService.findAnIdea(id);
    	this.userService.deleteIdea(idea);
    	return "redirect:/ideas";
    }
    
    @GetMapping("/like/{id}")
    public String likeIdea(@PathVariable("id") Long id, HttpSession session) {
    	Idea i = this.userService.findAnIdea(id);
    	Long loggedInUserId = (Long) session.getAttribute("userId");
    	User loggedInUser = userService.findUserById(loggedInUserId);
    	if (i.getLikes().contains(loggedInUser)) {
    		return "redirect:/ideas";
    	}
		UserIdeaLikes userIdea = new UserIdeaLikes(loggedInUser, i);
    	this.userService.createAssociation(userIdea);
    	return "redirect:/ideas";
    }
    
    @GetMapping("/unlike/{id}")
    public String unlikeIdea(@PathVariable("id") Long id, HttpSession session) {
    	Idea i = this.userService.findAnIdea(id);
    	Long loggedInUserId = (Long) session.getAttribute("userId");
    	User loggedInUser = userService.findUserById(loggedInUserId);
    	if (!i.getLikes().contains(loggedInUser)) {
    		return "redirect:/ideas";
    	}
    	loggedInUser.getIdeasLiked().remove(i);
    	this.userService.unlikeIdea(loggedInUser);
    	return "redirect:/ideas";
    }

}
