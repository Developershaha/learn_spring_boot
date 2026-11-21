package com.learnspringboot.service;

import com.learnspringboot.dto.CourseResponseDto;
import com.learnspringboot.dto.DepartmentRequestDto;
import com.learnspringboot.dto.DepartmentResponseDto;
import com.learnspringboot.dto.StudentResponseDto;
import com.learnspringboot.entity.Department;
import com.learnspringboot.exception.ResourceNotFoundException;
import com.learnspringboot.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    // save department
    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {
        Department department=new Department();
        department.setDepartmentName(requestDto.getDepartmentName());
        Department savedDepartment=departmentRepository.save(department);
        return mapToResponseDto(savedDepartment);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartment() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> mapToResponseDto(department)).toList();
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long id) {
        Department department=departmentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Department Not found with id "+id));
        return mapToResponseDto(department);
    }

    private DepartmentResponseDto mapToResponseDto(Department department){
        DepartmentResponseDto dto=new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());

        List<StudentResponseDto> students=department.getStudents()
                .stream()
                .map(student -> {
                    StudentResponseDto studentDto=new StudentResponseDto();
                    studentDto.setId(student.getId());
                    studentDto.setFirstName(student.getFirstName());
                    studentDto.setLastName(student.getLastName());
                    studentDto.setEmail(student.getEmail());
                    studentDto.setCourses(
                            student.getCourses()
                                    .stream()
                                    .map((course -> {
                                        CourseResponseDto courseDto = new CourseResponseDto();
                                        courseDto.setId(course.getId());
                                        courseDto.setCourseName(course.getCourseName());
                                        return courseDto;
                                    })).toList()
                    );
                    return  studentDto;
                }).toList();
        dto.setStudents(students);
        return dto;

    }
}
