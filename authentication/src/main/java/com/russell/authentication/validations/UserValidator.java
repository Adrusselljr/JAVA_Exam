package com.russell.authentication.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.russell.authentication.models.User;
import com.russell.authentication.services.UserService;

@Component
public class UserValidator implements Validator {
	
	private final UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
	// 1
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }
        if (this.userService.findByEmail(user.getEmail().toLowerCase()) != null) {
        	errors.rejectValue("email", "DuplicateEmail");
        }
    }

}
