package com.siddhesh.project.uber.uberApp.strategies.impl;

import com.siddhesh.project.uber.uberApp.dto.RideRequestDto;
import com.siddhesh.project.uber.uberApp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricingFareStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
