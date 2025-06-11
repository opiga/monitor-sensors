package com.example.MonitorSensors.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorInfoResponseDto {
    
    private String name;
    
    private String model;
    
    private Integer from;
    
    private Integer to;
    
    private String location;
    
    private String description;
    
    private String type;
    
    private String unit;
    
}