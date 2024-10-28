package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees") // parent route all bellow routes will be mounted here
public class EmployeeController {

    // not recommended
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // path is basically the route
    @GetMapping(path = "/{employeeId}") // pathvariable is mandatory to pass
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {// this pathvariable
                                                                                                    // annotation will
                                                                                                    // basically inject
                                                                                                    // that okay
                                                                                                    // employee comes
                                                                                                    // from path

        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id: " + id));
    }

    // Exception that is valid in this controller
    // @ExceptionHandler(NoSuchElementException.class)
    // public ResponseEntity<String> noEmployeeFound(NoSuchElementException
    // exception){
    // return new ResponseEntity<>("Employee Not Found " , HttpStatus.NOT_FOUND);
    // }

    @GetMapping // things which are not compulsory to show in webpage , meaning we can still run
                // our application those are passed as request param
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping // first it will validate this employeeDTO then go to first
    public ResponseEntity<EmployeeDTO> postEmployee(@RequestBody @Valid EmployeeDTO newEmployee) {

        EmployeeDTO employeeDTO = employeeService.postEmployee(newEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> putEmployee(@RequestBody @Valid EmployeeDTO employeeDTO,
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeebyId(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long employeeId) {
        boolean gotDeleted = employeeService.deleteById(employeeId);
        if (!gotDeleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(true);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@RequestBody Map<String, Object> updates,
            @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartiallyById(updates, employeeId);
        if (employeeDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
