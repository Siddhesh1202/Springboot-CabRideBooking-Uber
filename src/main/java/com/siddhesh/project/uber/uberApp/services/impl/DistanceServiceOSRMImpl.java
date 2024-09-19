package com.siddhesh.project.uber.uberApp.services.impl;

import com.siddhesh.project.uber.uberApp.services.DistanceService;
import org.springframework.data.geo.Point;

public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point sourcePoint, Point targetPoint) {
        return 0;
    }
}
