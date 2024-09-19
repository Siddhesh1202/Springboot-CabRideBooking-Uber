package com.siddhesh.project.uber.uberApp.controllers;

import com.siddhesh.project.uber.uberApp.dto.SignupDto;
import com.siddhesh.project.uber.uberApp.dto.UserDto;
import com.siddhesh.project.uber.uberApp.services.AuthService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signUp(@RequestBody SignupDto signupDto) {
        return authService.signUp(signupDto);
    }
}
