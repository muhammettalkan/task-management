package com.alkan.task_management.domain.auth.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
