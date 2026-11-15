package com.learnspringboot.controller;

import com.learnspringboot.entity.Student;
import com.learnspringboot.repository.StudentRepository;
import com.learnspringboot.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // get all the student
    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }


    // get student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // save student
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // update whole student fully update oky
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable Long id,@RequestBody Student student){
        return studentService.patchStudent(id,student);
    }


    // delete by id
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "student deleted";
    }


}
