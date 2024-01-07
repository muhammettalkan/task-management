package com.alkan.task_management.library.exception.custom;

public class EmailAlreadyTaken extends RuntimeException{
    public EmailAlreadyTaken(String message){
        super(message);
    }

}
