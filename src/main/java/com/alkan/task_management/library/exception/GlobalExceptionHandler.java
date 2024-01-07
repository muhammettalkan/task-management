package com.alkan.task_management.library.exception;

import com.alkan.task_management.library.common.BaseResponse;
import com.alkan.task_management.library.exception.custom.EmailAlreadyTaken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyTaken.class)
    public ResponseEntity<BaseResponse> emailAlreadyTaken(EmailAlreadyTaken e){
        BaseResponse response = new BaseResponse("1001 ", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }


}
