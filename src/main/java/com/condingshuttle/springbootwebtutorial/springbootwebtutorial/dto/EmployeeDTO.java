package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String name;
    private String email;
    private Long id;
    @JsonProperty("isActive")
    private Boolean isActive;
    private Integer age;
    private LocalDate dateOfJoining;
    //Note: we can also add validation logic also here if email is logic or not etc

}
