package com.example.MonitorSensors.controller;

import com.example.MonitorSensors.dto.request.SensorRequestDto;
import com.example.MonitorSensors.dto.response.SensorInfoResponseDto;
import com.example.MonitorSensors.service.SensorService;
import com.example.MonitorSensors.validation.OnCreate;
import com.example.MonitorSensors.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    
    private final SensorService sensorService;
    
    @PostMapping
    public ResponseEntity<SensorInfoResponseDto> createSensor(@Validated(OnCreate.class) @RequestBody SensorRequestDto sensor) {
        return ResponseEntity.ok(sensorService.save(sensor));
    }

    @GetMapping
    public ResponseEntity<List<SensorInfoResponseDto>> getAllSensors(@RequestParam(required = false) String name, @RequestParam(required = false) String model) {
        return ResponseEntity.ok(sensorService.searchByNameOrModel(name, model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorInfoResponseDto> getSensor(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorInfoResponseDto> updateSensor(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody SensorRequestDto sensor) {
        return ResponseEntity.ok(sensorService.update(id, sensor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}