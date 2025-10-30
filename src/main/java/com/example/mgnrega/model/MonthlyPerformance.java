package com.example.mgnrega.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyPerformance {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Column(name = "month_value")  // Avoid reserved word conflict
    private int month;

    @Column(name = "year_value")   // Avoid reserved word conflict
    private int year;

    private int workers;
    private double wagesPaid;
    private int households;

//    public MonthlyPerformance(Long id, District district, int month, int year, int workers, double wagesPaid, int households) {
//        this.id = id;
//        this.district = district;
//        this.month = month;
//        this.year = year;
//        this.workers = workers;
//        this.wagesPaid = wagesPaid;
//        this.households = households;
//    }
}
