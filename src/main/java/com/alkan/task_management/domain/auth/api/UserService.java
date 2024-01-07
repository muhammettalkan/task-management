package com.alkan.task_management.domain.auth.api;

import com.alkan.task_management.domain.auth.impl.User;
import com.alkan.task_management.domain.auth.web.LoginRequest;
import com.alkan.task_management.domain.auth.web.LoginResponse;
import com.alkan.task_management.domain.auth.web.RegisterRequest;

import java.util.List;

public interface UserService {
    User findById(Long id);

    UserDto find(Long id);

    List<UserDto> findAll();

    String register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    String delete(Long id);
}
