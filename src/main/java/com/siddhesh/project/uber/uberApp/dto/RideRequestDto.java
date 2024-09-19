package com.siddhesh.project.uber.uberApp.dto;

import com.siddhesh.project.uber.uberApp.entities.Rider;
import com.siddhesh.project.uber.uberApp.entities.enums.PaymentMethod;
import com.siddhesh.project.uber.uberApp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Point;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;
    private Point pickupLocation;
    private Point dopOffLocation;
    private LocalDateTime requestTime;
    private RiderDto rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}

