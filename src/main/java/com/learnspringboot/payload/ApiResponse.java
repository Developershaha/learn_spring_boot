package com.learnspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T>{ // used generic type T any data we can send in this class any data type we can send oky
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
}
