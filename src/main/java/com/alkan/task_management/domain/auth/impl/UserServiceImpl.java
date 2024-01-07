package com.alkan.task_management.domain.auth.impl;

import com.alkan.task_management.domain.auth.api.UserDto;
import com.alkan.task_management.domain.auth.api.UserService;
import com.alkan.task_management.domain.auth.web.LoginRequest;
import com.alkan.task_management.domain.auth.web.LoginResponse;
import com.alkan.task_management.domain.auth.web.RegisterRequest;
import com.alkan.task_management.domain.task.api.TaskService;
import com.alkan.task_management.library.enums.Role;
import com.alkan.task_management.library.exception.custom.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository, TaskService taskService , PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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
    @Override
    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email)) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = new User();
        user.setEmail(registerRequest.email);
        user.setPassword(passwordEncoder.encode(registerRequest.password));
        user.setUsername(registerRequest.username);

        Set<Role> authorities = new HashSet<>();
        authorities.add(Role.ROLE_USER);
        user.setAuthorities(authorities);
        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email , loginRequest.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findUserByEmail(loginRequest.email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", loginRequest.email)
        );

        LoginResponse response = new LoginResponse();
        response.setAuthorities(user.getAuthorities());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        return response;

    }
    @Override
    public String delete(Long id){
        userRepository.deleteById(id);
        return "User deleted successfully";
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

}
