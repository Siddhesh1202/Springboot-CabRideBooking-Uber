package com.siddhesh.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

@Entity
@Getter
@Setter
public class Driver {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;
    private String vehicleId;
    private Boolean available;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point currentLocation;
}
