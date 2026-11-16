
package com.learnspringboot.service;

import com.learnspringboot.dto.StudentRequestDto;
import com.learnspringboot.dto.StudentResponseDto;
import com.learnspringboot.entity.Student;
import com.learnspringboot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // Constructor injection
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE STUDENT
    @Override
    public StudentResponseDto saveStudent(StudentRequestDto dto) {

        Student student = new Student();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setPassword(dto.getPassword());
        student.setCreatedAt(LocalDateTime.now());
        Student savedStudent = studentRepository.save(student);

        return mapToResponseDto(savedStudent);
    }

    // GET ALL STUDENTS
    @Override
    public List<StudentResponseDto> getAllStudent() {

        return studentRepository.findAll()
                .stream()
                .map(student -> mapToResponseDto(student))
                .toList();
    }

    // GET STUDENT BY ID
    @Override
    public StudentResponseDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        return mapToResponseDto(student);
    }

    // DELETE STUDENT
    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        studentRepository.delete(student);
    }

    // FULL UPDATE (PUT)
    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setLastName(dto.getLastName());
        existingStudent.setEmail(dto.getEmail());
        existingStudent.setCourse(dto.getCourse());
        existingStudent.setPassword(dto.getPassword());
        existingStudent.setCreatedAt(LocalDateTime.now());
        Student updatedStudent = studentRepository.save(existingStudent);

        return mapToResponseDto(updatedStudent);
    }

    // PARTIAL UPDATE (PATCH)
    @Override
    public StudentResponseDto patchStudent(Long id, StudentRequestDto dto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        if (dto.getFirstName() != null) {
            existingStudent.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            existingStudent.setLastName(dto.getLastName());
        }

        if (dto.getEmail() != null) {
            existingStudent.setEmail(dto.getEmail());
        }

        if (dto.getCourse() != null) {
            existingStudent.setCourse(dto.getCourse());
        }
        if (dto.getPassword() != null) {
            existingStudent.setCourse(dto.getPassword());
        }


        Student updatedStudent = studentRepository.save(existingStudent);

        return mapToResponseDto(updatedStudent);
    }

    // ENTITY TO RESPONSE DTO MAPPING
    private StudentResponseDto mapToResponseDto(Student student) {

        StudentResponseDto dto = new StudentResponseDto();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setCourse(student.getCourse());

        return dto;
    }
}
