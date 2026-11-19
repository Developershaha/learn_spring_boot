package com.learnspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String state;

    private String country;

    @OneToOne(mappedBy = "address") // jpa got confuse and its bad practice tell the owning side oky and we can using join column we can indicate owning side here only send mapped by
    // mapped my write field name refer oky -- tell to the jpa only refer mapped by - we are not creating any table in this call and its inver side oky
    private Student student;
}
