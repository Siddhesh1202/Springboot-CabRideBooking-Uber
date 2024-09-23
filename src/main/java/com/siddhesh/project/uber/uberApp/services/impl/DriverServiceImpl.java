package com.siddhesh.project.uber.uberApp.services.impl;

import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.RideDto;
import com.siddhesh.project.uber.uberApp.dto.RiderDto;
import com.siddhesh.project.uber.uberApp.entities.Driver;
import com.siddhesh.project.uber.uberApp.entities.Ride;
import com.siddhesh.project.uber.uberApp.entities.RideRequest;
import com.siddhesh.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.siddhesh.project.uber.uberApp.entities.enums.RideStatus;
import com.siddhesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.siddhesh.project.uber.uberApp.repositories.DriverRepository;
import com.siddhesh.project.uber.uberApp.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final RideRequestService rideRequestService;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();
        if(!currentDriver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not available");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride Cannot be cancelled. Invalid Status : " + ride.getRideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailable(currentDriver, true);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto acceptRide(Long rideId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request status is not PENDING, status is " + rideRequest.getRideRequestStatus());
        }
        Driver currentDriver = getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver is not available");
        }
        Driver savedDriver = updateDriverAvailable(currentDriver, false);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not available");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not CONFIRMED, status is " + ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("OTP is not valid");
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        paymentService.createNewPayment(savedRide);
        ratingService.createNewRating(savedRide);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not available");
        }
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride status is not ONGOING, status is " + ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailable(driver, true);
        paymentService.processPayment(ride);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not the owner of the ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ENDED, status is " + ride.getRideStatus());
        }
        return ratingService.rateRider(ride, rating);
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getMyAllRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver, pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
    }

    @Override
    public Driver updateDriverAvailable(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }

}
