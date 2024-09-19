package com.siddhesh.project.uber.uberApp.strategies;

import com.siddhesh.project.uber.uberApp.dto.RideRequestDto;
import com.siddhesh.project.uber.uberApp.entities.Driver;
import com.siddhesh.project.uber.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto)
}
