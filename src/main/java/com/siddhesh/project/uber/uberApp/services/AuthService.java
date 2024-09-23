package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.OnBoardDriverDto;
import com.siddhesh.project.uber.uberApp.dto.SignupDto;
import com.siddhesh.project.uber.uberApp.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}