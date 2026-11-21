package com.learnspringboot.repository;

import com.learnspringboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

// jpa is interface oky so that why we need to create interface oky send class name and send primary key


// inside this repository we will get lots of inbuild method oky we can create custom method as well as
public interface DepartmentRepository extends JpaRepository<Department,Long> {
// we can write default method we say query method



}
