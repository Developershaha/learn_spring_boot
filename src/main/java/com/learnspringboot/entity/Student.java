package com.learnspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    // entity used for database operation data base model
    // dto used for api communication model api model
    private String password;
    private LocalDateTime createdAt;

}
