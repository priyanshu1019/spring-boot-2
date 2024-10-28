package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "title of Department can't be blank")
    @Size(max = 20, message = "Name of Department should be less than 20 in length")
    private String title;

    @JsonProperty("isActive")
    @AssertTrue(message = "isActive has to be true")
    private Boolean isActive;

    @PastOrPresent(message = "createdAt should be of past or present")
    private LocalDate createdAt;

}
