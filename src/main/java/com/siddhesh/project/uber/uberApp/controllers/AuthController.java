package com.siddhesh.project.uber.uberApp.controllers;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.OnBoardDriverDto;
import com.siddhesh.project.uber.uberApp.dto.SignupDto;
import com.siddhesh.project.uber.uberApp.dto.UserDto;
import com.siddhesh.project.uber.uberApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
        return new ResponseEntity<>(authService.signUp(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnBoardDriverDto onBoardDriverDto) {
        return new ResponseEntity<>(authService.onBoardDriver(userId, onBoardDriverDto), HttpStatus.CREATED);
    }
}
