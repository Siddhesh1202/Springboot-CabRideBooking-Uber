package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.entities.RideRequest;


public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);
    void update(RideRequest rideRequest);
}
