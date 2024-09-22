package com.siddhesh.project.uber.uberApp.services.impl;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.SignupDto;
import com.siddhesh.project.uber.uberApp.dto.UserDto;
import com.siddhesh.project.uber.uberApp.entities.User;
import com.siddhesh.project.uber.uberApp.entities.enums.Role;
import com.siddhesh.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.siddhesh.project.uber.uberApp.repositories.UserRepository;
import com.siddhesh.project.uber.uberApp.services.AuthService;
import com.siddhesh.project.uber.uberApp.services.RiderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signUp(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeConflictException("User already exists");
        }

        User mappeduser = modelMapper.map(signupDto, User.class);
        mappeduser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappeduser);
        riderService.createNewRider(savedUser);
        //TODO Add wallet
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onBoardDriver(String userId) {
        return null;
    }
}
