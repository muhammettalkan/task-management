package com.alkan.task_management.library.common;

public class BaseResponse {

    public String code;
    public String message;

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
