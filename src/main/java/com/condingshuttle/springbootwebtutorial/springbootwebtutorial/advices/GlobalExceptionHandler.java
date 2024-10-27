package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFound(ResourceNotFoundException exception){

        ApiError apiError = ApiError.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message(exception.getMessage())
                            .build();

        return buildErrorResponseEntity(apiError);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){

        ApiError apiError = ApiError.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message(exception.getMessage())
                            .build();

        return buildErrorResponseEntity(apiError);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationError(MethodArgumentNotValidException exception){
        List<String> errors = exception
                            .getBindingResult()
                            .getAllErrors()
                            .stream()
                            .map(error-> error.getDefaultMessage())
                            .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Validation errors encountered")
                            .subErrors(errors)
                            .build();

        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError) , apiError.getStatus());
    }

}
