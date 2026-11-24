
package com.learnspringboot.service;

import com.learnspringboot.dto.StudentRequestDto;
import com.learnspringboot.dto.StudentResponseDto;

import java.util.List;

public interface  StudentService {

    StudentResponseDto saveStudent(StudentRequestDto dto);

    List<StudentResponseDto> getAllStudent();

    StudentResponseDto getStudentById(Long id);

    void deleteStudent(Long id);

    StudentResponseDto updateStudent(Long id, StudentRequestDto dto);

    StudentResponseDto patchStudent(Long id, StudentRequestDto dto);

    StudentResponseDto getStudentByEmail(String email);
    Long countStudentsByCourseName(String courseName);
    void deleteStudentByEmail(String email);
}
