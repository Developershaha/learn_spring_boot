
package com.learnspringboot.controller;

import com.learnspringboot.dto.StudentRequestDto;
import com.learnspringboot.dto.StudentResponseDto;
import com.learnspringboot.service.StudentService;
import jakarta.validation.Valid;
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

        // responseEntity its is a wrapper class oky ResponseEntity.ok( 201 created 200 found 404 not found 400 bad request  500 internal error

        // 3xx redirection messages
        // 4xx client error
        // 5xx server error

        return ResponseEntity.ok(studentService.getAllStudent());
    }

    // GET STUDENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(
            @PathVariable Long id) {
        StudentResponseDto student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // CREATE STUDENT
    @PostMapping
    public ResponseEntity<StudentResponseDto> saveStudent(@Valid
                                                          @RequestBody StudentRequestDto dto) {

        StudentResponseDto response = studentService.saveStudent(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // FULL UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid
            @RequestBody StudentRequestDto dto) {

        StudentResponseDto student = studentService.updateStudent(id, dto);
        if(student==null){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // PARTIAL UPDATE (PATCH) - no need to apply valid here
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
