package com.learnspringboot.dto;

import com.learnspringboot.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String course;
    private AddressResponseDto address;
    private String departmentName;
}
