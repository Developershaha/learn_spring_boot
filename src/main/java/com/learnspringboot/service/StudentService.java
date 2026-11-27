
package com.learnspringboot.service;

import com.learnspringboot.dto.StudentProjectionDto;
import com.learnspringboot.dto.StudentRequestDto;
import com.learnspringboot.dto.StudentResponseDto;
import com.learnspringboot.projection.StudentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface   StudentService {

    StudentResponseDto saveStudent(StudentRequestDto dto);

    List<StudentResponseDto> getAllStudent();

    StudentResponseDto getStudentById(Long id);

    void deleteStudent(Long id);

    StudentResponseDto updateStudent(Long id, StudentRequestDto dto);

    StudentResponseDto patchStudent(Long id, StudentRequestDto dto);

    StudentResponseDto getStudentByEmail(String email);
    Long countStudentsByCourseName(String courseName);
    void deleteStudentByEmail(String email);
    List<StudentResponseDto>getStudentFirstNameAndCourse(String firstName,String course);
    StudentResponseDto getStudentByEmailNative(String email);

    List<StudentResponseDto> getAllStudentSortedByFirstName();

    Page<StudentResponseDto> getStudent(Pageable pageable);
    List<StudentProjectionDto> findAllStudentProjections();

    //
    public List<StudentResponseDto> searchStudents(String firstName,String email,String course);
}
