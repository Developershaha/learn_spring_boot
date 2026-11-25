package com.learnspringboot.repository;

import com.learnspringboot.entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);
    // write derived query
    Optional<Student> findByEmail(String email);
//    Long countByCourse(String courses); // select* from course where course=? below query is JPQL query oky
@Query("""
        SELECT COUNT(s)
        FROM Student s
        JOIN s.courses c
        WHERE LOWER(c.courseName) = LOWER(:courseName)
    """)
Long countStudentsByCourseName(@Param("courseName") String courseName);
void deleteByEmail(String email);

List<Student> findByFirstNameAndCourses(String firstName,String courses);

// write Native SQL query oky
    @Query(value = "SELECT * FROM students WHERE email=:email", // work on database not on objects oky
    nativeQuery = true)
    Optional<Student> findStudentByEmailNative(@Param("email")String email);

}
