package com.learnspringboot.service;

import com.learnspringboot.dto.DepartmentRequestDto;
import com.learnspringboot.dto.DepartmentResponseDto;
import com.learnspringboot.entity.Department;
import com.learnspringboot.exception.ResourceNotFoundException;
import com.learnspringboot.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

// save department
    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {
        Department department=new Department();
        department.setDepartmentName(requestDto.getDepartmentName());
        Department savedDepartment=departmentRepository.save(department);

        return new DepartmentResponseDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName()
        );
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartment() {
        return departmentRepository.findAll()
                .stream()
                .map( department -> new DepartmentResponseDto(
                        department.getId(),
                        department.getDepartmentName()
                )).toList();
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long id) {
        Department department=departmentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Department Not found with id "+id));
        return new DepartmentResponseDto(department.getId(),
                department.getDepartmentName()
        );
    }
}
