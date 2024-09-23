package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.RiderDto;
import com.siddhesh.project.uber.uberApp.entities.Driver;
import com.siddhesh.project.uber.uberApp.entities.Ride;
import com.siddhesh.project.uber.uberApp.entities.Rider;
import org.springframework.stereotype.Service;


public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);
}
