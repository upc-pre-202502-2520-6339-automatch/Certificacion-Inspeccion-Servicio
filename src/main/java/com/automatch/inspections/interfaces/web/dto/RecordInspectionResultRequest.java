package com.automatch.inspections.interfaces.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RecordInspectionResultRequest(

        @Schema(example = "true") boolean passed,

        @Schema(example = "Todo en orden") String notes) {
}