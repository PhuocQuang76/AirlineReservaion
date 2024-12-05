package com.synergisticit.validation;

import com.synergisticit.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User)target;
            //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "user.userId.null", "User Id must be present");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty", "User name is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "Password is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty", "Email is required.");
//
//            if(user.getRoles().isEmpty()) {
//                errors.rejectValue("roles", "user.roles.empty", "You did not select any role.");
//            }
        }

    

}
