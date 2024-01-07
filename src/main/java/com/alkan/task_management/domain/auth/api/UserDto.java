package com.alkan.task_management.domain.auth.api;

import com.alkan.task_management.domain.task.api.TaskDto;

import java.util.List;

public class UserDto {

    public Long id;
    public String username;
    public String password;
    public String email;
    public List<TaskDto> taskList;

    public UserDto() {
    }
}
