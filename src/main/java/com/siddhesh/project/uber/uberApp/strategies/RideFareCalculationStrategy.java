package com.siddhesh.project.uber.uberApp.strategies;

import com.siddhesh.project.uber.uberApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
