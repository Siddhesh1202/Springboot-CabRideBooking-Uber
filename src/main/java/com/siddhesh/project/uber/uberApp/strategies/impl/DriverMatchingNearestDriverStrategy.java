package com.siddhesh.project.uber.uberApp.strategies.impl;

import com.siddhesh.project.uber.uberApp.dto.RideRequestDto;
import com.siddhesh.project.uber.uberApp.entities.Driver;
import com.siddhesh.project.uber.uberApp.entities.RideRequest;
import com.siddhesh.project.uber.uberApp.repositories.DriverRepository;
import com.siddhesh.project.uber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findTenNearestMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestMatchingDrivers(rideRequest.getPickupLocation());
    }
}
