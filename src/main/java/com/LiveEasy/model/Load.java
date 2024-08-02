package com.LiveEasy.model;

// Load.java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID shipperId;
    private String loadingPoint;
    private String unloadingPoint;
    private String productType;
    private String truckType;
    private Integer noOfTrucks;
    private Integer weight;

    // getters and setters
}
