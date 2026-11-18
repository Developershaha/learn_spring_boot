
package com.learnspringboot.exception;

import com.learnspringboot.payload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // STUDENT NOT FOUND - 404
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleStudentNotFoundException(
            StudentNotFoundException ex,
            HttpServletRequest request) {

        ApiResponse<Void> response = new ApiResponse<>(
                false,
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // DUPLICATE EMAIL - 409
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateEmailException(
            DuplicateEmailException ex,
            HttpServletRequest request) {

        ApiResponse<Void> response = new ApiResponse<>(
                false,
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // FIELD VALIDATION - 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    errors.put(
                            fieldError.getField(),
                            fieldError.getDefaultMessage()
                    );
                });

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                false,
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // INVALID JSON - 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidJson(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ApiResponse<Void> response = new ApiResponse<>(
                false,
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid request body. Please check your JSON format.",
                null
        );

        return ResponseEntity.badRequest().body(response);
    }
}
