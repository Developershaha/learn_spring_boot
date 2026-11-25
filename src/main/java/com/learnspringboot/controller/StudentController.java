
package com.learnspringboot.controller;

import com.learnspringboot.dto.StudentRequestDto;
import com.learnspringboot.dto.StudentResponseDto;
import com.learnspringboot.payload.ApiResponse;
import com.learnspringboot.service.StudentService;

import jakarta.validation.Valid;
import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET ALL STUDENTS
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudent() {

        List<StudentResponseDto> students =
                studentService.getAllStudent();

        ApiResponse<List<StudentResponseDto>> response =
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Students fetched successfully",
                        students
                );

        return ResponseEntity.ok(response);
    }

    // GET STUDENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentById(
            @PathVariable Long id) {

        StudentResponseDto student =
                studentService.getStudentById(id);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Student fetched successfully",
                        student
                );

        return ResponseEntity.ok(response);
    }

    // GET STUDENT BY EMAIL ID
    @GetMapping("/email")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentByEmailId(
            @RequestParam String email) {

        StudentResponseDto student =
                studentService.getStudentByEmail(email);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Student fetched successfully By email",
                        student
                );

        return ResponseEntity.ok(response);
    }


    // CREATE STUDENT
    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDto>> saveStudent(
            @Valid @RequestBody StudentRequestDto dto) {

        StudentResponseDto student =
                studentService.saveStudent(dto);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Student created successfully",
                        student
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // FULL UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto dto) {

        StudentResponseDto student =
                studentService.updateStudent(id, dto);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Student updated successfully",
                        student
                );

        return ResponseEntity.ok(response);
    }

    // PARTIAL UPDATE (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> patchStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDto dto) {

        StudentResponseDto student =
                studentService.patchStudent(id, dto);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Student partially updated successfully",
                        student
                );

        return ResponseEntity.ok(response);
    }

    // DELETE STUDENT
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Student deleted successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse> getStudentByCourse(@RequestParam String courseName){

        Long count=studentService.countStudentsByCourseName(courseName);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Student count feched sucessfully",
                        count
                )
        );

    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<ApiResponse<String>> deleteStudentByEmail(@PathVariable String email){
        studentService.deleteStudentByEmail(email);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Student deleted successfully ",
                        null
                )
        );
    }
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDto>> getStudents(@RequestParam String firstName,@RequestParam String course){


        return ResponseEntity.ok(
                studentService.getStudentFirstNameAndCourse(firstName,course)
        );
    }
    @GetMapping("/native/email/{email}")
    public ResponseEntity<StudentResponseDto> getStudentBYEmailNative(@PathVariable String email){
        StudentResponseDto studentResponseDto=studentService.getStudentByEmailNative(email);
        return ResponseEntity.ok(studentResponseDto);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<StudentResponseDto>> getSortedStudent(){
        return ResponseEntity.ok(studentService.getAllStudentSortedByFirstName());
    }
}
