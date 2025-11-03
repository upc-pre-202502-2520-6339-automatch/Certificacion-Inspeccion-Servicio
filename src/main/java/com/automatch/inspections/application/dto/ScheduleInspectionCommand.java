package com.automatch.inspections.application.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleInspectionCommand(
        @NotNull LocalDateTime scheduledAt,
        @NotNull UUID inspectorId) {
}