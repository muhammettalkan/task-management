package com.alkan.task_management.domain.auth.api;

import com.alkan.task_management.domain.auth.impl.User;

public interface UserService {
    User findById(Long id);

    UserDto find(Long id);
}
