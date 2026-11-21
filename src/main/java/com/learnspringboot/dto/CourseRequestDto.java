package com.learnspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {

    @NotBlank(message = "course name is required")
    private String courseName;

    @NotBlank(message = "duration is required")
    private String duration;

    @NotBlank(message = "frees is required")
    private Double fees;

    @NotBlank(message = "Instructor name is required")
    private String instructorName;


}
