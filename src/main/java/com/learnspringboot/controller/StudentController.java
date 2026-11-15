
package com.learnspringboot.controller;

import com.learnspringboot.dto.StudentRequestDto;
import com.learnspringboot.dto.StudentResponseDto;
import com.learnspringboot.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET ALL STUDENTS
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudent() {

        return ResponseEntity.ok(studentService.getAllStudent());
    }

    // GET STUDENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // CREATE STUDENT
    @PostMapping
    public ResponseEntity<StudentResponseDto> saveStudent(
            @RequestBody StudentRequestDto dto) {

        StudentResponseDto response = studentService.saveStudent(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // FULL UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDto dto) {

        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    // PARTIAL UPDATE (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponseDto> patchStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDto dto) {

        return ResponseEntity.ok(studentService.patchStudent(id, dto));
    }

    // DELETE STUDENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}
