package com.example.MonitorSensors.dto.mapper;

import com.example.MonitorSensors.dto.request.SensorRequestDto;
import com.example.MonitorSensors.dto.response.SensorInfoResponseDto;
import com.example.MonitorSensors.entity.Sensor;
import com.example.MonitorSensors.entity.SensorType;
import com.example.MonitorSensors.entity.SensorUnit;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {SensorRangeMapper.class})
public interface SensorMapper {
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "type", qualifiedByName = "stringToSensorType")
    @Mapping(source = "unit", target = "unit", qualifiedByName = "stringToSensorUnit")
    Sensor toEntity(SensorRequestDto dto);
    
    @Mapping(source = "type", target = "type", qualifiedByName = "sensorTypeToString")
    @Mapping(source = "unit", target = "unit", qualifiedByName = "sensorUnitToString")
    SensorInfoResponseDto toDto(Sensor sensor);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "type", qualifiedByName = "stringToSensorType")
    @Mapping(source = "unit", target = "unit", qualifiedByName = "stringToSensorUnit")
    void updateSensorFromDto(SensorRequestDto dto, @MappingTarget Sensor sensor);
    
    
    @Named("stringToSensorType")
    default SensorType mapSensorType(String typeName) {
        if (typeName == null) return null;
        SensorType type = new SensorType();
        type.setName(typeName);
        return type;
    }
    
    @Named("stringToSensorUnit")
    default SensorUnit mapSensorUnit(String unitName) {
        if (unitName == null) return null;
        SensorUnit unit = new SensorUnit();
        unit.setName(unitName);
        return unit;
    }
    
    @Named("sensorTypeToString")
    default String mapTypeToString(SensorType type) {
        return type != null ? type.getName() : null;
    }
    
    @Named("sensorUnitToString")
    default String mapUnitToString(SensorUnit unit) {
        return unit != null ? unit.getName() : null;
    }
}