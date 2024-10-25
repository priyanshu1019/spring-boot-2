package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//if this name is not given then the entity name is taken as table name
//in entity we define what table to be created and fields
//we can also add constraints here of tables
@Table(name = "employees")
public class EmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @JsonProperty("isActive")
    private Boolean isActive;
    private Integer age;
    private LocalDate dateOfJoining;
}
