package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.services.DepartmentServices;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId) {

        Optional<DepartmentDTO> departmentDto = departmentServices.getDepartmentById(departmentId);
        return departmentDto
                .map(departmentDto1 -> ResponseEntity.ok(departmentDto1))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentServices.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> postADepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {

        DepartmentDTO departmentDTO2 = departmentServices.createDepartment(departmentDTO);
        return new ResponseEntity<>(departmentDTO2, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> putDepartment(@PathVariable Long departmentId,
            @RequestBody @Valid DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentServices.updateDepartment(departmentId, departmentDTO));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentServices.deleteDepartmentById(departmentId));

    }

}
