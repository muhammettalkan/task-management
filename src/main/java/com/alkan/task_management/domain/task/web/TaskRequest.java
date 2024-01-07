package com.alkan.task_management.domain.task.web;

import com.alkan.task_management.library.enums.Status;

public class TaskRequest {

    public String title;
    public String description;
    public Status status = Status.TODO;

    public TaskRequest() {
    }
}
