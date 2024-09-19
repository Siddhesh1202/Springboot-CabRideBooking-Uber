package com.siddhesh.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Service
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

}
