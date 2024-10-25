package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {RoleValidator.class}
)
public @interface RoleValidationAnnotation {
    String message() default "Role of the Employee can either be ADMIN or USER ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
