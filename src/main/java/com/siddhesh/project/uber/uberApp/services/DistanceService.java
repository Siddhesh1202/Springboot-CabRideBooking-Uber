package com.siddhesh.project.uber.uberApp.services;

import org.springframework.data.geo.Point;

public interface DistanceService  {
    double calculateDistance(Point sourcePoint, Point targetPoint);
}
