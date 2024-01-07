package com.alkan.task_management.domain.auth.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
