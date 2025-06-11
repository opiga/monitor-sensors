package com.example.MonitorSensors.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sensor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
   
    private String model;
    
    @Embedded
    private SensorRange range;
    
    @ManyToOne
    @JoinColumn(name = "type_id")
    private SensorType type;
    
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private SensorUnit unit;
    
    private String location;
    
    private String description;
}