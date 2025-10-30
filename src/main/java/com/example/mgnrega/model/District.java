package com.example.mgnrega.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data                       // Generates getters, setters, toString, equals, etc.
@NoArgsConstructor          // Creates a no-argument constructor
@AllArgsConstructor          // Creates a constructor with all fields
public class District {

    @Id
    private Long id;          // District ID
    private String name;      // District name
    private String state;     // State name
    private double lat;       // Latitude
    private double lng;       // Longitude

//    public District() {}
//
//    public District(Long id, String name, String state, double lat, double lng) {
//        this.id = id;
//        this.name = name;
//        this.state = state;
//        this.lat = lat;
//        this.lng = lng;
//    }
}
