package com.siddhesh.project.uber.uberApp.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private Long userId;
    private String email;
    private String password;
}
