package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.RideDto;
import com.siddhesh.project.uber.uberApp.dto.RiderDto;
import com.siddhesh.project.uber.uberApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
    RideDto cancelRide(Long rideId);
    RideDto acceptRide(Long rideRequestId);
    RideDto startRide(Long rideId, String otp);
    RideDto endRide(Long rideId);
    RiderDto rateRider(Long rideId, Integer rating);
    DriverDto getMyProfile();
    Page<RideDto> getMyAllRides(PageRequest pageRequest);
    Driver getCurrentDriver();
    Driver updateDriverAvailability(Driver driver, boolean available);
    Driver createNewDriver(Driver driver);
}
