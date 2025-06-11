package com.example.MonitorSensors.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class SensorRange {
    
    @Column(name = "range_from", nullable = false)
    private Integer from;
    
    @Column(name = "range_to", nullable = false)
    private Integer to;
    
}
