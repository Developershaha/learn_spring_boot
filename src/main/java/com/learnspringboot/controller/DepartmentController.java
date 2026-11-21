package com.learnspringboot.controller;

import com.learnspringboot.dto.DepartmentRequestDto;
import com.learnspringboot.dto.DepartmentResponseDto;
import com.learnspringboot.entity.Department;
import com.learnspringboot.payload.ApiResponse;
import com.learnspringboot.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/departements")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> createDepartment(@Valid @RequestBody DepartmentRequestDto requestDto){
        DepartmentResponseDto response=departmentService.createDepartment(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Department Created successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> getAllDepartment(){
     List<DepartmentResponseDto> response=departmentService.getAllDepartment();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Department fetch successfully",
                        response
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> getDepartmentById(@PathVariable Long id){
        DepartmentResponseDto response=departmentService.getDepartmentById(id);
        ApiResponse<DepartmentResponseDto> apiResponse=new ApiResponse<>(
                true,
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Department fetch successfully",
                response

        );
        return ResponseEntity.ok(apiResponse);
    }
}
