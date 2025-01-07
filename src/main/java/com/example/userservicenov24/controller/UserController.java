package com.example.userservicenov24.controller;

import com.example.userservicenov24.dtos.LoginRequestDto;
import com.example.userservicenov24.dtos.SignUpRequestDto;
import com.example.userservicenov24.dtos.UserResponseDto;
import com.example.userservicenov24.models.Token;
import com.example.userservicenov24.models.User;
import com.example.userservicenov24.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        System.out.println("signUpRequestDto = " + signUpRequestDto);
        User user = userService.signUp(
                signUpRequestDto.getName(),
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword()
        );
        return UserResponseDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @GetMapping("/validate/{token}")
    public UserResponseDto validate(@PathVariable String token) {
        User user = userService.validateToken(token);
        return UserResponseDto.from(user);
    }
}
