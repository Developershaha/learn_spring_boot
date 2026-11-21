package com.learnspringboot.controller;

import com.learnspringboot.dto.CourseRequestDto;
import com.learnspringboot.dto.CourseResponseDto;
import com.learnspringboot.payload.ApiResponse;
import com.learnspringboot.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponseDto>> createCourse(@Valid @RequestBody CourseRequestDto dto){
        CourseResponseDto response=courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Course created successfully",
                        response
                )
        );
    }

  @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> getAllCourses(){
        return  ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Course fetched successfully",
                        courseService.getAllCourses()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> getCourseById(@PathVariable Long id){
        return  ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Course fetched successfully",
                        courseService.getCourseById(id)
                )
        );
    }
}
