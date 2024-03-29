package com.alkan.task_management.domain.task.web;

import com.alkan.task_management.library.enums.Status;

public class TaskResponse {

    public Long id;
    public String title;
    public String description;
    public Status status;
    public Long userId;

    public TaskResponse() {
    }
}
