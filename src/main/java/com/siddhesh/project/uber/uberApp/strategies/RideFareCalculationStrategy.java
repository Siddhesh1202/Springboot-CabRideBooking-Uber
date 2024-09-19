package com.siddhesh.project.uber.uberApp.strategies;

import com.siddhesh.project.uber.uberApp.entities.RideRequest;
import org.springframework.stereotype.Service;


public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
