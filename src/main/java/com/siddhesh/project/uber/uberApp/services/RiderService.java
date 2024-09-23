package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.RideDto;
import com.siddhesh.project.uber.uberApp.dto.RideRequestDto;
import com.siddhesh.project.uber.uberApp.dto.RiderDto;
import com.siddhesh.project.uber.uberApp.entities.Rider;
import com.siddhesh.project.uber.uberApp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface RiderService {
    RideDto cancelRide(Long rideId);
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    DriverDto rateDriver(Long rideId, Integer rating);
    RiderDto getMyProfile();
    Page<RideDto> getMyAllRides(PageRequest pageRequest);
    Rider createNewRider(User user);

    Rider getCurrentRider();
}
