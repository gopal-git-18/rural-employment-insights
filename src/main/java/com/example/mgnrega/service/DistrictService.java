package com.example.mgnrega.service;

import com.example.mgnrega.model.District;
import com.example.mgnrega.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    // Fetch all districts
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    // ✅ Find nearest district to a given lat/lng using Haversine formula
    public District findNearestDistrict(double lat, double lng) {
        List<District> districts = districtRepository.findAll();
        return districts.stream()
                .min(Comparator.comparing(d -> distance(lat, lng, d.getLat(), d.getLng())))
                .orElse(null);
    }

    // Haversine formula to calculate distance between two coordinates
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // distance in km
    }
}
