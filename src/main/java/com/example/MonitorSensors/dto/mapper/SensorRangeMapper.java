package com.example.MonitorSensors.dto.mapper;

import com.example.MonitorSensors.dto.request.SensorRangeDto;
import com.example.MonitorSensors.entity.SensorRange;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorRangeMapper {
    
    SensorRange toEntity(SensorRangeDto dto);
}