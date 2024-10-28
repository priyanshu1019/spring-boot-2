package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data//this is shorthand for getting getter and setter and some other annotations in lombok
@Builder
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> subErrors;

}
