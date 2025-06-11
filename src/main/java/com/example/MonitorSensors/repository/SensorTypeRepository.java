package com.example.MonitorSensors.repository;

import com.example.MonitorSensors.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {
    
    Optional<SensorType> findByName(String name);
}