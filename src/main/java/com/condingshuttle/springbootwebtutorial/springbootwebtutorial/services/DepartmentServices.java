package com.condingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.condingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;

@Service
public class DepartmentServices {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServices(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {

        List<DepartmentEntity> list = departmentRepository.findAll();
        return list
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());

    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(departmentEntity);

        return modelMapper.map(savedDepartment, DepartmentDTO.class);

    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        return departmentEntity.map(departmentEntity1 -> modelMapper.map(departmentEntity1, DepartmentDTO.class));

    }

    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {

        isExistsById(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartment = departmentRepository.save(departmentEntity);

        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public Boolean deleteDepartmentById(Long departmentId) {

        isExistsById(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;

    }

    boolean isExistsById(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if (!exists)
            throw new ResourceNotFoundException("Department not found with id: " + id);

        return true;
    }

}
