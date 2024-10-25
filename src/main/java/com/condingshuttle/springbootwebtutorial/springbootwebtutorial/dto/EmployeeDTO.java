package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.Annotations.RoleValidationAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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

    private Long id;

    @NotBlank(message = "Name of the Employee can not be blank")//message is displayed when error occurs
    @Size(min= 3 , max = 20 , message = "Name of Employee should be in range 3 to 20")
    private String name;

    @NotBlank(message = "Email of the Employee can not be Blank")
    @Email(message = "Email is not valid")
    private String email;

    @JsonProperty("isActive")
    @AssertTrue(message = "isActive has to be true")
    private Boolean isActive;

    @Max(value = 70 , message = "Employee age should be less than 70")
    @Min(value = 18 , message = "Employee age should be more than 18")
    private Integer age;

    @NotBlank(message = "Required Field in Employee : Role")
    //custom validator annotation
    @RoleValidationAnnotation
//    @Pattern(regexp = "^(ADMIN|USER)$" , message = "Role of Employee can by User or Admin")//all regex start with ^ and ends with $
    private String role;

    @NotNull(message = "Salary of Employee can not be null")
    @Positive(message = "Salary of Employee should be greater than zero")
    @Digits(integer = 6 , fraction = 2 , message = "Salary of Employee should be of 6 figure and 2 decimals")
    @DecimalMin(value = "1000.99")
    @DecimalMax(value="10000.99")
    private Double Salary;

    @PastOrPresent(message = "Date of Joining can not be a future date")
    private LocalDate dateOfJoining;

}
