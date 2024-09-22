package com.siddhesh.project.uber.uberApp.services.impl;

import com.siddhesh.project.uber.uberApp.entities.RideRequest;
import com.siddhesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.siddhesh.project.uber.uberApp.repositories.RideRequestRepository;
import com.siddhesh.project.uber.uberApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository rideRequestRepository;
    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(() -> new ResourceNotFoundException("Ride Request Not Found"));
    }

    @Override
    public void update(RideRequest rideRequest) {
        findRideRequestById(rideRequest.getId());
        rideRequestRepository.save(rideRequest);
    }
}
