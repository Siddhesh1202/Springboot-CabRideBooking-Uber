package com.siddhesh.project.uber.uberApp.services.impl;

import com.siddhesh.project.uber.uberApp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point sourcePoint, Point targetPoint) {
        try {
            String uri = sourcePoint.getX() + "," +  sourcePoint.getY() + ";" + targetPoint.getX() + "," + targetPoint.getY();
            OSRMDistanceDto osrmDistanceDto = RestClient.builder().baseUrl(OSRM_API).build().get()
                    .uri(uri).retrieve().body(OSRMDistanceDto.class);

            return osrmDistanceDto.getRoutes().get(0).getDistance();
        } catch (Exception e) {
            throw new RuntimeException("Error in getting data from OSRM" + e.getMessage());
        }

    }
}
@Data
class OSRMDistanceDto{
    private List<OSRMRoutes> routes;
}
@Data
class OSRMRoutes{
    private Double distance;
}