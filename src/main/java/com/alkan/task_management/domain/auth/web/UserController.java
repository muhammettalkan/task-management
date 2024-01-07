package com.alkan.task_management.domain.auth.web;

import com.alkan.task_management.domain.auth.api.UserDto;
import com.alkan.task_management.domain.auth.api.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = {"/register", "/signup"})
    public String register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PostMapping(value = {"/login", "/signin"})
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> getAllUsers(){
        return userService.findAll();
    }
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDto getUser(@PathVariable Long id){
        return userService.find(id);
    }
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable Long id){
        return userService.delete(id);
    }


}
