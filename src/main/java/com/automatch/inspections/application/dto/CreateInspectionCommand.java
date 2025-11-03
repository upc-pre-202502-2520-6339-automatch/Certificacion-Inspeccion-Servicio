package com.automatch.inspections.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateInspectionCommand(
        @NotNull UUID vehicleId // NO se manda id de inspección, solo del vehículo
) {
}