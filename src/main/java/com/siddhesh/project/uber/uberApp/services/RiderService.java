package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.RideDto;
import com.siddhesh.project.uber.uberApp.dto.RideRequestDto;
import com.siddhesh.project.uber.uberApp.dto.RiderDto;
import com.siddhesh.project.uber.uberApp.entities.Rider;
import com.siddhesh.project.uber.uberApp.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RiderService {
    RideDto cancelRide(Long rideId);
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    DriverDto rateDriver(Long rideId, Integer rating);
    RiderDto getMyProfile();
    List<RideDto> getMyAllRides();
    Rider createNewRider(User user);

    Rider getCurrentRider();
}
