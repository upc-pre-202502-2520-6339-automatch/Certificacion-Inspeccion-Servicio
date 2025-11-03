package com.automatch.inspections.application.usecase;

import com.automatch.inspections.application.dto.ScheduleInspectionCommand;
import com.automatch.inspections.domain.model.Inspection;
import com.automatch.inspections.domain.model.vo.InspectorId;
import com.automatch.inspections.domain.ports.InspectionRepository;
import java.util.UUID;

public class ScheduleInspectionUseCase {
    private final InspectionRepository repository;

    public ScheduleInspectionUseCase(InspectionRepository repository) {
        this.repository = repository;
    }

    public Inspection handle(UUID inspectionId, ScheduleInspectionCommand cmd) {
        var inspection = repository.findById(inspectionId)
                .orElseThrow(() -> new IllegalArgumentException("Inspección no encontrada"));
        inspection.schedule(cmd.scheduledAt(), new InspectorId(cmd.inspectorId()));
        return repository.save(inspection);
    }
}