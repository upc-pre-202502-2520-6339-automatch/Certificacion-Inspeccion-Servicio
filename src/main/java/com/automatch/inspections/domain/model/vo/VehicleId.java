package com.automatch.inspections.domain.model.vo;

import java.util.UUID;

public record VehicleId(UUID value) {
    public VehicleId {
        if (value == null)
            throw new IllegalArgumentException("VehicleId requerido");
    }
}