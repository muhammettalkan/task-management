package com.alkan.task_management.domain.task.api;

import java.util.List;

public interface TaskService {
    List<TaskDto> findByUserId(Long id);

    List<TaskDto> findAll();

    String create(TaskDto taskDto);

    String delete(Long id);

    String update(TaskDto taskDto);
}
