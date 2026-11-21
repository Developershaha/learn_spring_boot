package com.learnspringboot.service;

import com.learnspringboot.dto.CourseRequestDto;
import com.learnspringboot.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto responseDto);
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto getCourseById(Long id);
}
