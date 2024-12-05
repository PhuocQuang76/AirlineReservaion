package com.synergisticit.validation;

import com.synergisticit.domain.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Role role = (Role) target;
    }
}
