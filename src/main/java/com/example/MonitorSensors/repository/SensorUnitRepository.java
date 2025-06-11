package com.example.MonitorSensors.repository;

import com.example.MonitorSensors.entity.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SensorUnitRepository extends JpaRepository<SensorUnit, Long> {
    
    Optional<SensorUnit> findByName(String name);
}