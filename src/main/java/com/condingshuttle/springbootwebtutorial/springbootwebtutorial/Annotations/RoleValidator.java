package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class RoleValidator implements ConstraintValidator< RoleValidationAnnotation, String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roles = List.of("ADMIN" , "USER");
        return roles.contains(inputRole);
    }
}
