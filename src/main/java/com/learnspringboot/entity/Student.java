package com.learnspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String course;

    // no need to show to password to the end user

    //dto transfer only required data oky no need to show all the data

    // entity used for database operation database model
    // dto used for api communication model api model
    private String password;
    private LocalDateTime createdAt;

    // establish relationship between two tables oky address class and working ORM mapping

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER , orphanRemoval = true) // we can establish one direction mean unidirection flow means student understand address
    // PERSIST ,MERGE,REMOVE - deleted operation referesh detach -  - eager by default get all both the table oky
    // lazy - student fist load address not load oky  unnnessasary remove the record
    // generally we establish unidirection but we can establish bi-direction relationship
    @JoinColumn(name = "address_id") // this annotation defined and establish connection between two tables oky
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id") // we are keeping id in this table because to reduce data redudancy oky
    private Department department;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // owner side
            @JoinTable(
                    name = "student_courses",
                    joinColumns=@JoinColumn(name = "student_id"),
                    inverseJoinColumns = @JoinColumn(name = "course_id")
            )
    List<Course> courses =new ArrayList<>();

}
