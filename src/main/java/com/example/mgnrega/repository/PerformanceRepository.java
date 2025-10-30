package com.example.mgnrega.repository;

import com.example.mgnrega.model.MonthlyPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<MonthlyPerformance, Long> {
    List<MonthlyPerformance> findByDistrictId(Long districtId);
}
