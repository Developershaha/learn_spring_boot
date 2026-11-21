
package com.learnspringboot.service;

import com.learnspringboot.dto.*;
import com.learnspringboot.entity.Address;
import com.learnspringboot.entity.Course;
import com.learnspringboot.entity.Department;
import com.learnspringboot.entity.Student;
import com.learnspringboot.exception.DuplicateEmailException;
import com.learnspringboot.exception.ResourceNotFoundException;
import com.learnspringboot.exception.StudentNotFoundException;
import com.learnspringboot.repository.CourseRepository;
import com.learnspringboot.repository.DepartmentRepository;
import com.learnspringboot.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    // we can apply validation on three types

    // 1) client validation server validation and database validation we can apply validation on three layer
    // server side validation bean validation before data saved in the database oky

    // first add dependency oky then work on this

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;

    @Override
    public StudentResponseDto saveStudent(StudentRequestDto dto) {

        String email = dto.getEmail().trim().toLowerCase();
        boolean emailExists = studentRepository.existsByEmail(email);
        if (emailExists) {
            throw new DuplicateEmailException(
                    "Student already exists with email: " + email
            );
        }

        // address entity address
        Address address = new Address();
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setCountry(dto.getAddress().getCountry());


        // department entity
        Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(() ->
                new ResourceNotFoundException("Department Not found with id " + dto.getDepartmentId())
        );

        // Course Entity

        List<Course> courses=courseRepository.findAllById(dto.getCourseIds());


        // student entity
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(email);
        student.setPassword(dto.getPassword());
        student.setCreatedAt(LocalDateTime.now());
        student.setAddress(address);
        student.setDepartment(department);
        student.setCourses(courses);

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
                        new StudentNotFoundException("Student not found with id: " + id));
        return mapToResponseDto(student);
    }

    // DELETE STUDENT
    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));

        studentRepository.delete(student);
    }

    // FULL UPDATE (PUT)
    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));

        // duplicate email check
        if (studentRepository.existsByEmail(dto.getEmail()) && !existingStudent.getEmail().equals(dto.getEmail())) {
            throw new DuplicateEmailException("student already exist with email " + dto.getEmail());
        }

        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setLastName(dto.getLastName());
        existingStudent.setEmail(dto.getEmail());
        existingStudent.setPassword(dto.getPassword());

        // address update
        Address address = existingStudent.getAddress();
        if (address == null) {
            address = new Address();
        }
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setCountry(dto.getAddress().getCountry());
        existingStudent.setCreatedAt(LocalDateTime.now());
        existingStudent.setAddress(address);

        // department update

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department Not found with id " + dto.getDepartmentId())
                );
        existingStudent.setDepartment(department);
        Student updatedStudent = studentRepository.save(existingStudent);

        return mapToResponseDto(updatedStudent);
    }

    // PARTIAL UPDATE (PATCH)
    @Override
    public StudentResponseDto patchStudent(Long id, StudentRequestDto dto) {


        // student path
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));

        if (dto.getFirstName() != null) {
            existingStudent.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            existingStudent.setLastName(dto.getLastName());
        }

        if (dto.getEmail() != null) {
            if (studentRepository.existsByEmail(dto.getEmail()) && !existingStudent.getEmail().equals(dto.getEmail())) {
                throw new DuplicateEmailException("student already exist with email " + dto.getEmail());
            }
            existingStudent.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null) {
            existingStudent.setPassword(dto.getPassword());
        }

        // address patch

        if (dto.getAddress() != null) {
            Address address = existingStudent.getAddress();
            if (address == null) {
                address = new Address();
            }
            if (dto.getAddress().getCity() != null) {
                address.setCity(dto.getAddress().getCity());
            }
            if (dto.getAddress().getCountry() != null) {
                address.setCountry(dto.getAddress().getCountry());
            }
            if (dto.getAddress().getState() != null) {
                address.setState(dto.getAddress().getState());
            }
            existingStudent.setAddress(address);
        }

        // department path
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department Not found with id " + dto.getDepartmentId()));
            existingStudent.setDepartment(department);
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
        AddressResponseDto addressDto = new AddressResponseDto();
        addressDto.setCity(student.getAddress().getCity());
        addressDto.setCountry(student.getAddress().getCountry());
        addressDto.setState(student.getAddress().getState() );
        dto.setAddress(addressDto);
        dto.setDepartmentName(student.getDepartment().getDepartmentName());
        dto.setCourses(
                student.getCourses()
                        .stream()
                        .map(course -> new CourseResponseDto(
                                course.getId(),
                                course.getCourseName(),
                                course.getDuration(),
                                course.getFees(),
                                course.getInstructorName()
                        ))
                        .toList()
        );
        return dto;
    }
}
