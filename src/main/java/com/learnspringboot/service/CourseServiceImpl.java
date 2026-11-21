package com.learnspringboot.service;

import com.learnspringboot.dto.CourseRequestDto;
import com.learnspringboot.dto.CourseResponseDto;
import com.learnspringboot.entity.Course;
import com.learnspringboot.exception.ResourceNotFoundException;
import com.learnspringboot.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    @Override
    public CourseResponseDto createCourse(CourseRequestDto responseDto) {

        Course course=new Course();
        course.setCourseName(responseDto.getCourseName());
        course.setDuration(responseDto.getDuration());
        course.setFees(responseDto.getFees());
        course.setInstructorName(responseDto.getInstructorName());

        Course savedCourser=courseRepository.save(course);
        return mapToResponseDto(savedCourser);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map((course -> mapToResponseDto(course)))
                .toList();
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(" Course not found with id : "+id));
        return mapToResponseDto(course);
    }

    private CourseResponseDto mapToResponseDto(Course course){
        CourseResponseDto responseDto=new CourseResponseDto();
        responseDto.setId(course.getId());
        responseDto.setCourseName(course.getCourseName());
        responseDto.setDuration(course.getDuration());
        responseDto.setFees(course.getFees());
        responseDto.setInstructorName(course.getInstructorName());
        return responseDto;

    }
}
