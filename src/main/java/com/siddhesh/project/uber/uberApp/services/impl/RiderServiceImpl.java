package com.siddhesh.project.uber.uberApp.services.impl;

import com.siddhesh.project.uber.uberApp.configs.MapperConfig;
import com.siddhesh.project.uber.uberApp.dto.DriverDto;
import com.siddhesh.project.uber.uberApp.dto.RideDto;
import com.siddhesh.project.uber.uberApp.dto.RideRequestDto;
import com.siddhesh.project.uber.uberApp.dto.RiderDto;
import com.siddhesh.project.uber.uberApp.entities.RideRequest;
import com.siddhesh.project.uber.uberApp.entities.Rider;
import com.siddhesh.project.uber.uberApp.entities.User;
import com.siddhesh.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.siddhesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.siddhesh.project.uber.uberApp.repositories.RideRequestRepository;
import com.siddhesh.project.uber.uberApp.repositories.RiderRepository;
import com.siddhesh.project.uber.uberApp.services.RiderService;
import com.siddhesh.project.uber.uberApp.strategies.DriverMatchingStrategy;
import com.siddhesh.project.uber.uberApp.strategies.RideFareCalculationStrategy;
import com.siddhesh.project.uber.uberApp.strategies.StrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper ;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final StrategyManager strategyManager;
    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto , RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Rider rider = getCurrentRider();
        Double fare = strategyManager.getRideFareStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        strategyManager.getDriverMatchingStrategy(rider.getRating()).findTenNearestMatchingDrivers(rideRequest);
        log.info(rideRequest.toString());
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getMyAllRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO : Spring Security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found - " + 1));
    }
}
