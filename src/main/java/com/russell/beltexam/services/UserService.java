package com.russell.beltexam.services;

import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.russell.beltexam.models.Idea;
import com.russell.beltexam.models.User;
import com.russell.beltexam.models.UserIdeaLikes;
import com.russell.beltexam.repositories.IdeaRepository;
import com.russell.beltexam.repositories.UserIdeaLikesRepository;
import com.russell.beltexam.repositories.UserRepository;

@Service
public class UserService {
	
	 private final UserRepository userRepository;
	 private final IdeaRepository ideaRepository;
	 private final UserIdeaLikesRepository userIdeaLikesRepository;
	    
	 public UserService(UserRepository userRepository, IdeaRepository ideaRepository, UserIdeaLikesRepository userIdeaLikesRepository) {
	     this.userRepository = userRepository;
	     this.ideaRepository = ideaRepository;
	     this.userIdeaLikesRepository = userIdeaLikesRepository;
	 }
	    
	 // register user and hash their password
	 public User registerUser(User user) {
	     String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	     user.setPassword(hashed);
	     user.setEmail(user.getEmail().toLowerCase());
	     return userRepository.save(user);
	 }
	    
	 // find user by email
	 public User findByEmail(String email) {
	      return userRepository.findByEmail(email);
	 }
	    
	 // find user by id
	 public User findUserById(Long id) {
		 Optional<User> u = userRepository.findById(id);
		 if(u.isPresent()) {
			 return u.get();
	    }
		 else {
	    	 return null;
	    }
	 }
	    
	 // authenticate user
	 public boolean authenticateUser(String email, String password) {
	     // first find the user by email
	     User user = userRepository.findByEmail(email);
	     // if we can't find it by email, return false
	     if(user == null) {
	         return false;
	     }
	     else {
	         // if the passwords match, return true, else, return false
	         if(BCrypt.checkpw(password, user.getPassword())) {
	             return true;
	         }
	         else {
	             return false;
	         }
	     }
	 }
	 
	 // returns a list of all users
	 public List<User> findAllUsers() {
		 return (List<User>)this.userRepository.findAll();
	 }
	 
	 // create a new idea
	 public Idea createIdea(Idea idea) {
		 return this.ideaRepository.save(idea);
	 }
	 
	 //returns a list of all ideas
	 public List<Idea> findAllIdeas() {
		 return (List<Idea>)this.ideaRepository.findAll();
	 }
	 
	 // shows one idea
	 public Idea findAnIdea(Long id) {
		 return this.ideaRepository.findById(id).orElse(null);
	 }
	 
	// updates an idea
	 public Idea updateIdea(Idea idea) {
		 return this.ideaRepository.save(idea);
	 }
	 
	// deletes an idea
	 public void deleteIdea(Idea idea) {
		 this.ideaRepository.delete(idea);
	 }
	 
	// save member to group joined
	 public UserIdeaLikes createAssociation(UserIdeaLikes userIdea) {
		 return this.userIdeaLikesRepository.save(userIdea);
	 }
	 
	 // leave group
	 public User unlikeIdea(User user) {
		 return this.userRepository.save(user);
	 }

}
