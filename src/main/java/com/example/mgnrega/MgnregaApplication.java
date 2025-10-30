package com.example.mgnrega;

import com.example.mgnrega.model.District;
import com.example.mgnrega.model.MonthlyPerformance;
import com.example.mgnrega.repository.DistrictRepository;
import com.example.mgnrega.repository.PerformanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class MgnregaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MgnregaApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(DistrictRepository districtRepo, PerformanceRepository perfRepo) {
        return args -> {
            //  Step 1: Create sample districts (for Rajasthan)
            List<District> districts = Arrays.asList(
                    new District(1L, "Jaipur", "Rajasthan", 26.9124, 75.7873),
                    new District(2L, "Udaipur", "Rajasthan", 24.5854, 73.7125),
                    new District(3L, "Jodhpur", "Rajasthan", 26.2389, 73.0243),
                    new District(4L, "Kota", "Rajasthan", 25.2138, 75.8648),
                    new District(5L, "Bikaner", "Rajasthan", 28.0229, 73.3119)
            );
            districtRepo.saveAll(districts);

            // ✅ Step 2: Generate random monthly performance data for each district (2024–2025)
            Random random = new Random();
            long idCounter = 1L;

            for (District d : districts) {
                for (int year = 2024; year <= 2025; year++) {
                    for (int month = 1; month <= 12; month++) {
                        int workers = 4000 + random.nextInt(2000);
                        double wages = 800000 + random.nextDouble() * 500000;
                        int households = 1000 + random.nextInt(1000);

                        MonthlyPerformance p = new MonthlyPerformance(
                                idCounter++, d, month, year, workers, wages, households
                        );
                        perfRepo.save(p);
                    }
                }
            }

            System.out.println("Sample data loaded: " + districts.size() + " districts, multiple months per year.");
        };
    }
}
