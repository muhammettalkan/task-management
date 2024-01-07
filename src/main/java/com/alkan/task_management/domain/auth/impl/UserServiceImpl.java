package com.alkan.task_management.domain.auth.impl;

import com.alkan.task_management.domain.auth.api.UserDto;
import com.alkan.task_management.domain.auth.api.UserService;
import com.alkan.task_management.domain.task.api.TaskService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskService taskService;

    public UserServiceImpl(UserRepository userRepository, TaskService taskService) {
        this.userRepository = userRepository;
        this.taskService = taskService;
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    @Override
    public UserDto find(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return toDto(user);
    }
    private UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.email = user.getEmail();
        userDto.taskList = taskService.findByUserId(user.getId());
        return userDto;
    }
    public User register(UserDto userDto){
        if (userRepository.existsByUsername(userDto.username)){
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(userDto.email)){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setId(userDto.id);
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        user.setEmail(userDto.email);
        return user;
    }

}
