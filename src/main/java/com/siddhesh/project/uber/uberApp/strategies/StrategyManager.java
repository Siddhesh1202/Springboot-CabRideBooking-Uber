package com.siddhesh.project.uber.uberApp.strategies;

import com.siddhesh.project.uber.uberApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.siddhesh.project.uber.uberApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.siddhesh.project.uber.uberApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.siddhesh.project.uber.uberApp.strategies.impl.RideFareSurgePricingFareStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class StrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestStrategy;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareStrategy rideFareSurgePricingFareStrategy;
    public DriverMatchingStrategy getDriverMatchingStrategy(double riderRating) {
        if(riderRating > 4.8){
            return driverMatchingHighestRatedStrategy;
        }
        return driverMatchingNearestStrategy;
    }

    public RideFareCalculationStrategy getRideFareStrategy() {
        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurgeTime){
            return rideFareSurgePricingFareStrategy;
        }
        return rideFareDefaultFareCalculationStrategy;

    }

}
