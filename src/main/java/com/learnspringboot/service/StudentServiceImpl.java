package com.learnspringboot.service;

import com.learnspringboot.entity.Student;
import com.learnspringboot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student exitingStudent = studentRepository.findById(id).orElse(null);
        if (exitingStudent != null) {
            exitingStudent.setFirstName(student.getFirstName());
            exitingStudent.setLastName(student.getLastName());
            exitingStudent.setEmail(student.getEmail());
            exitingStudent.setCourse(student.getCourse());
            return studentRepository.save(exitingStudent);

        }
        return null;
    }

    @Override
    public Student patchStudent(Long id, Student student) {
        Student exitingStudent = studentRepository.findById(id).orElse(null);
        if (exitingStudent != null) {
            if (exitingStudent.getFirstName() != null) exitingStudent.setFirstName(student.getFirstName());
            if (exitingStudent.getLastName() != null) exitingStudent.setLastName(student.getLastName());
            if (exitingStudent.getEmail() != null) exitingStudent.setEmail(student.getEmail());
            if (exitingStudent.getCourse() != null) exitingStudent.setCourse(student.getCourse());
            return studentRepository.save(exitingStudent);

        }
        return null;
    }
}
