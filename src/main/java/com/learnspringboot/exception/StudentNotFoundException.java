package com.learnspringboot.exception;

public class StudentNotFoundException extends RuntimeException{
    // generally we need to handle runtime exception

    public StudentNotFoundException(String msg){
        super(msg);
    }
}
