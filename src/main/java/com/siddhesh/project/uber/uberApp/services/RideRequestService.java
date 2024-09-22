package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.entities.RideRequest;
import org.springframework.stereotype.Service;


public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);
    void update(RideRequest rideRequest);
}
