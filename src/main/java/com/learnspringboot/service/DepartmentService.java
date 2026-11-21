package com.learnspringboot.service;

import com.learnspringboot.dto.DepartmentRequestDto;
import com.learnspringboot.dto.DepartmentResponseDto;
import com.learnspringboot.repository.DepartmentRepository;

import java.util.List;

public interface DepartmentService {
    DepartmentRepository createDepartment(DepartmentRequestDto requestDto);
    List<DepartmentResponseDto> getAllDepartment();
    DepartmentResponseDto getDepartmentById(Long id);
}
