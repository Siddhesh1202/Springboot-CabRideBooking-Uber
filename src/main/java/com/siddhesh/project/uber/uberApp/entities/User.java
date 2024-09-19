package com.siddhesh.project.uber.uberApp.entities;

import com.siddhesh.project.uber.uberApp.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Set;

@Entity
@Getter
@Service
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
