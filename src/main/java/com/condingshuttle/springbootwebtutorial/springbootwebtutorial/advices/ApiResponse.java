package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data//because everything private so getters and setters are required
//<T> is generic means we dont know actually what type of data we get so for all type of data this will work
public class ApiResponse<T> {
    @JsonFormat(pattern = "hh:mm:ss dd-MM-YYYY")//so that jackson will know in which format it has to display
    private LocalDateTime t;
    //either we will get error from api or data so two diff constructor
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.t = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

}
