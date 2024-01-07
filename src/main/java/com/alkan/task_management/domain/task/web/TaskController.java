package com.alkan.task_management.domain.task.web;

import com.alkan.task_management.domain.task.api.TaskDto;
import com.alkan.task_management.domain.task.api.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER' , 'ROLE_ADMIN')")
    public List<TaskDto> getTaskById(@PathVariable Long id){
        return taskService.findByUserId(id);
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_USER' , 'ROLE_ADMIN')")
    public String create(@RequestBody TaskDto taskDto){
        return taskService.create(taskDto);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER' , 'ROLE_ADMIN')")
    public String delete(@PathVariable Long id){
        return taskService.delete(id);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_USER' , 'ROLE_ADMIN')")
    public String update(@RequestBody TaskDto taskDto){
        return taskService.update(taskDto);
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<TaskDto> getAll(){
        return taskService.findAll();
    }
}
