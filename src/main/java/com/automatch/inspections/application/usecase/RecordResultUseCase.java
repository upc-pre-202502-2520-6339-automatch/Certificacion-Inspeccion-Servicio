package com.automatch.inspections.application.usecase;

import com.automatch.inspections.application.dto.RecordResultCommand;
import com.automatch.inspections.domain.model.Inspection;
import com.automatch.inspections.domain.ports.InspectionRepository;
import java.util.UUID;

public class RecordResultUseCase {
    private final InspectionRepository repository;

    public RecordResultUseCase(InspectionRepository repository) {
        this.repository = repository;
    }

    public Inspection handle(UUID inspectionId, RecordResultCommand cmd) {
        var inspection = repository.findById(inspectionId)
                .orElseThrow(() -> new IllegalArgumentException("Inspección no encontrada"));
        inspection.recordResult(cmd.passed(), cmd.notes());
        return repository.save(inspection);
    }
}