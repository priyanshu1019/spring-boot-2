package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));

    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity , EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO postEmployee(EmployeeDTO newEmployee) {
        EmployeeEntity employeeEntity = modelMapper.map(newEmployee , EmployeeEntity.class);
         EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
         return modelMapper.map(savedEmployee , EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeebyId(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO , EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        return modelMapper.map(employeeRepository.save(employeeEntity) , EmployeeDTO.class);
    }
    public boolean isExistsById(Long id){
        return employeeRepository.existsById(id);
    }
    public boolean deleteById(Long employeeId) {
        boolean exists = isExistsById(employeeId);
        if( !exists)return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartiallyById(Map<String, Object> updates, Long employeeId) {
        boolean exists = isExistsById(employeeId);
        if( !exists)return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field , value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class , field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated , employeeEntity , value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity) , EmployeeDTO.class);
    }
}
