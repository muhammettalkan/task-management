package com.alkan.task_management.domain.task.impl;

import com.alkan.task_management.domain.auth.api.UserService;
import com.alkan.task_management.domain.task.api.TaskDto;
import com.alkan.task_management.domain.task.api.TaskService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final @Lazy UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, @Lazy UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }
    @Override
    public List<TaskDto> findByUserId(Long id){
        return taskRepository.findAllByUserId(id)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<TaskDto> findAll(){
        return taskRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public String create(TaskDto taskDto){
        Task task = toEntity(taskDto);
        taskRepository.save(task);
        return "Task is created";
    }
    @Override
    public String delete(Long id){
        taskRepository.deleteById(id);
        return "Task is deleted";
    }
    @Override
    public String update(TaskDto taskDto){
        Task task = toEntity(taskDto);
        taskRepository.save(task);
        return "Task is updated";
    }
    private TaskDto toDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.id = task.getId();
        taskDto.title = task.getTitle();
        taskDto.description = task.getDescription();
        taskDto.status = task.getStatus();
        taskDto.userId = task.getUser().getId();
        return taskDto;
    }

    private Task toEntity(TaskDto dto){
        Task task = new Task();
        task.setId(dto.id);
        task.setTitle(dto.title);
        task.setDescription(dto.description);
        task.setStatus(dto.status);
        task.setUser(userService.findById(dto.userId));
        return task;
    }
}
