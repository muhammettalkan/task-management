package com.alkan.task_management.library.exception;

import com.alkan.task_management.library.common.BaseResponse;
import com.alkan.task_management.library.exception.custom.EmailAlreadyTaken;
import com.alkan.task_management.library.exception.custom.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyTaken.class)
    public ResponseEntity<BaseResponse> emailAlreadyTaken(EmailAlreadyTaken e) {
        BaseResponse response = new BaseResponse("1001 ", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse> resourceNotFound(ResourceNotFoundException e) {
        BaseResponse response = new BaseResponse("404", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

}
