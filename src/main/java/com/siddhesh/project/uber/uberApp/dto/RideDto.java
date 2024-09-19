package com.siddhesh.project.uber.uberApp.dto;

import com.siddhesh.project.uber.uberApp.entities.Driver;
import com.siddhesh.project.uber.uberApp.entities.Rider;
import com.siddhesh.project.uber.uberApp.entities.enums.PaymentMethod;
import com.siddhesh.project.uber.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.geolatte.geom.Point;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class RideDto {

    private Long id;

    private Point pickupLocation;

    private Point dopOffLocation;

    private LocalDateTime createdTime;

    private RiderDto riderDto;

    private DriverDto driverDto;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
