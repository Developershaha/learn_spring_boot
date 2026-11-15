package com.learnspringboot.controller;

import com.learnspringboot.entity.Student;
import com.learnspringboot.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // save student
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // get all the student
    @GetMapping
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // get student by id 
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    // delete by id
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "student deleted";
    }


}
