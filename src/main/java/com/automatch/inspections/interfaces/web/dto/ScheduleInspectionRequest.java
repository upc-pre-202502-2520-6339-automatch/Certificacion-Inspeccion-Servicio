package com.automatch.inspections.interfaces.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleInspectionRequest(

        @Schema(example = "2025-12-01T15:30:00") LocalDateTime when,

        @Schema(example = "cd11e599-79c1-4d0d-b9a3-8f6d873fc7fb") UUID inspectorId

) {
}