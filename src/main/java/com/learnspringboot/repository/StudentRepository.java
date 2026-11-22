package com.learnspringboot.repository;

import com.learnspringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);
    // write derived query
    Optional<Student> findByEmail(String email);
}
