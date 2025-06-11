package com.example.MonitorSensors.dto.request;

import com.example.MonitorSensors.validation.OnCreate;
import com.example.MonitorSensors.validation.OnUpdate;
import com.example.MonitorSensors.validation.ValidRange;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ValidRange(groups = {OnCreate.class, OnUpdate.class})
public class SensorRangeDto {
    
    @Positive(groups = {OnCreate.class, OnUpdate.class})
    private Integer from;
    
    @Positive(groups = {OnCreate.class, OnUpdate.class})
    private Integer to;
    
}