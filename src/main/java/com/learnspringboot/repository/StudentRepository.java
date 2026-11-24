package com.learnspringboot.repository;

import com.learnspringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);
    // write derived query
    Optional<Student> findByEmail(String email);

//    Long countByCourse(String courses); // select* from course where course=?
@Query("""
        SELECT COUNT(s)
        FROM Student s
        JOIN s.courses c
        WHERE LOWER(c.courseName) = LOWER(:courseName)
    """)
Long countStudentsByCourseName(@Param("courseName") String courseName);
void deleteByEmail(String email);

}
