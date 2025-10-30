package com.example.mgnrega.controller;

import com.example.mgnrega.model.District;
import com.example.mgnrega.model.MonthlyPerformance;
import com.example.mgnrega.repository.DistrictRepository;
import com.example.mgnrega.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/districts")
@CrossOrigin(origins = "*") // allows frontend to access APIs
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private PerformanceRepository perfRepo;

    // ✅ Get all districts
    @GetMapping
    public List<District> getAllDistricts() {
        return districtRepo.findAll();
    }
    @Autowired
    private com.example.mgnrega.service.DistrictService districtService;

    // ✅ Get performance data for a district
    @GetMapping("/{id}/performance")
    public List<MonthlyPerformance> getPerformance(@PathVariable Long id) {
        return perfRepo.findByDistrictId(id);
    }
    // ✅ Auto-detect nearest district using coordinates
    @GetMapping("/nearest")
    public District getNearestDistrict(@RequestParam double lat, @RequestParam double lng) {
        return districtService.findNearestDistrict(lat, lng);
    }
}
