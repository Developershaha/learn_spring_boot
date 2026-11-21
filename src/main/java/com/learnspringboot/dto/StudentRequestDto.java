package com.learnspringboot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
    @NotBlank(message = "first name is required")
    @Size(min = 2,max = 20 , message = "first name must be between 2 and 30 character")
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(min = 2,max = 20 , message = "first name must be between 2 and 30 character")
    private String lastName;


    @NotBlank(message = "email name is required")
    @Email(message = "please enter valid email")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;
    private AddressRequestDTO address;

    @NotNull(message = "Department Id is required")
    private Long departmentId;

    @NotEmpty(message = "At least one course required")
    private List<Long> courseIds;
}
