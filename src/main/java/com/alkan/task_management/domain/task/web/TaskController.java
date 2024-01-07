package com.alkan.task_management.domain.task.web;

import com.alkan.task_management.domain.task.api.TaskDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER' , 'ROLE_ADMIN')")
    public String getTaskById(@PathVariable String id){
        return "sdfsdf";
    }
}
