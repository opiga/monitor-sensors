package com.example.MonitorSensors.validation;

import com.example.MonitorSensors.dto.request.SensorRangeDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<ValidRange, SensorRangeDto> {
    
    @Override
    public boolean isValid(SensorRangeDto range, ConstraintValidatorContext context) {
        if (range == null) return true;
        return range.getFrom() < range.getTo();
    }
}