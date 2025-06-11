package com.example.MonitorSensors.dto.request;

import com.example.MonitorSensors.validation.OnCreate;
import com.example.MonitorSensors.validation.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SensorRequestDto {
    
    @NotBlank(groups = OnCreate.class)
    @Size(min = 3, max = 30)
    private String name;
    
    @NotBlank(groups = OnCreate.class)
    @Size(max = 15)
    private String model;
    
    @Valid
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private SensorRangeDto range;
    
    @Size(max = 40)
    private String location;
    
    @Size(max = 200)
    private String description;
    
    @NotBlank(groups = OnCreate.class)
    private String type;
    
    private String unit;
    
}