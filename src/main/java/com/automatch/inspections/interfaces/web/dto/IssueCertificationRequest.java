package com.automatch.inspections.interfaces.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

public record IssueCertificationRequest(

        @Schema(example = "31c3f3bb-7a11-4793-a1c5-9c69b7db58f0") UUID vehicleId,

        @Schema(example = "2026-06-01") LocalDate expiresAt

) {
}