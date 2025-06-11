package com.example.MonitorSensors.service;

import com.example.MonitorSensors.dto.mapper.SensorMapper;
import com.example.MonitorSensors.dto.request.SensorRequestDto;
import com.example.MonitorSensors.dto.response.SensorInfoResponseDto;
import com.example.MonitorSensors.entity.Sensor;
import com.example.MonitorSensors.entity.SensorType;
import com.example.MonitorSensors.entity.SensorUnit;
import com.example.MonitorSensors.repository.SensorRepository;
import com.example.MonitorSensors.repository.SensorTypeRepository;
import com.example.MonitorSensors.repository.SensorUnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {
    
    private final SensorRepository sensorRepository;
    
    private final SensorTypeRepository sensorTypeRepository;
    
    private final SensorUnitRepository sensorUnitRepository;
    
    private final SensorMapper sensorMapper;
    
    public SensorInfoResponseDto save(SensorRequestDto dto) {
        SensorType sensorType = getSensorTypeOrThrow(dto.getType());
        SensorUnit sensorUnit = (dto.getUnit() == null) ? null : getSensorUnitOrThrow(dto.getUnit());

        Sensor sensor = sensorMapper.toEntity(dto);
        sensor.setType(sensorType);
        sensor.setUnit(sensorUnit);

        return sensorMapper.toDto(sensorRepository.save(sensor));
    }
    
    public List<SensorInfoResponseDto> searchByNameOrModel(String name, String model) {
        List<Sensor> sensors = sensorRepository.findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(Optional.ofNullable(name).orElse(""),
                Optional.ofNullable(model).orElse(""));

        return sensors.stream()
                      .map(sensorMapper::toDto)
                      .collect(Collectors.toList());
    }
    
    public SensorInfoResponseDto findById(Long id) {
        return sensorMapper.toDto(getSensorOrThrow(id));
    }

    public SensorInfoResponseDto update(Long id, SensorRequestDto dto) {
        Sensor sensor = getSensorOrThrow(id);

        sensorMapper.updateSensorFromDto(dto, sensor);

        Optional.ofNullable(dto.getType())
                .map(this::getSensorTypeOrThrow)
                .ifPresent(sensor::setType);

        Optional.ofNullable(dto.getUnit())
                .map(this::getSensorUnitOrThrow)
                .ifPresent(sensor::setUnit);

        return sensorMapper.toDto(sensorRepository.save(sensor));
    }
    
    public void delete(Long id) {
        Sensor sensor = getSensorOrThrow(id);
        sensorRepository.delete(sensor);
    }
    
    private Sensor getSensorOrThrow(Long id) {
        return sensorRepository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException("Sensor not found with id: " + id));
    }
    
    private SensorType getSensorTypeOrThrow(String typeName) {
        if (typeName == null || typeName.isBlank()) {
            throw new IllegalArgumentException("Sensor type must be provided.");
        }
        return sensorTypeRepository.findByName(typeName)
                                   .orElseThrow(() -> new EntityNotFoundException("Sensor type not found: " + typeName));
    }
    
    private SensorUnit getSensorUnitOrThrow(String unitName) {
        if (unitName == null || unitName.isBlank()) {
            return null;
        }
        return sensorUnitRepository.findByName(unitName)
                                   .orElseThrow(() -> new EntityNotFoundException("Sensor unit not found: " + unitName));
    }
}