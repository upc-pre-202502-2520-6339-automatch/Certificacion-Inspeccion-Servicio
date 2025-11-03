package com.automatch.inspections.domain.model.vo;

import java.util.UUID;

public record InspectorId(UUID value) {
    public InspectorId {
        if (value == null)
            throw new IllegalArgumentException("InspectorId requerido");
    }
}