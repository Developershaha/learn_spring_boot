package com.learnspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {

    @NotBlank(message = "Department name is required")
    @Size(max = 100,message = "Department name can not exceed 100 characters")
    private String departmentName;
}
