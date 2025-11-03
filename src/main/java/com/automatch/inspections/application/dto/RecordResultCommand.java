package com.automatch.inspections.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecordResultCommand(
        @NotNull Boolean passed,
        @NotBlank String notes) {
}