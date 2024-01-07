package com.alkan.task_management.domain.task.api;

import com.alkan.task_management.library.enums.Status;

public class TaskDto {
    public Long id;
    public String title;
    public String description;
    public Status status;
    public Long userId;

    public TaskDto() {
    }
}
