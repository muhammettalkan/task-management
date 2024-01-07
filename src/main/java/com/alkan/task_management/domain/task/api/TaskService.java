package com.alkan.task_management.domain.task.api;

import java.util.List;

public interface TaskService {
    List<TaskDto> findByUserId(Long id);
}
