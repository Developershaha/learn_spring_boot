package com.learnspringboot.dto;

import com.learnspringboot.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponseDto address;
    private String departmentName;
    private List<CourseResponseDto> courses;
}
