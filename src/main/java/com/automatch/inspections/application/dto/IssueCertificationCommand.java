package com.automatch.inspections.application.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record IssueCertificationCommand(
        @NotNull LocalDate expiresAt) {
}