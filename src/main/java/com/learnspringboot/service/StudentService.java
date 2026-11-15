package com.learnspringboot.service;

import com.learnspringboot.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudent();
    Student getStudentById(Long id);
    void deleteStudent(Long id);

}
