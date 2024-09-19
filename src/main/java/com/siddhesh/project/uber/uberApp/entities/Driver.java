package com.siddhesh.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Service
public class Driver {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

    private Boolean available;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point currentLocation;
}
