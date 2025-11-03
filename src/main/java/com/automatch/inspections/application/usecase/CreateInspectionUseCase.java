package com.automatch.inspections.application.usecase;

import com.automatch.inspections.application.dto.CreateInspectionCommand;
import com.automatch.inspections.domain.model.Inspection;
import com.automatch.inspections.domain.model.vo.VehicleId;
import com.automatch.inspections.domain.ports.InspectionRepository;

public class CreateInspectionUseCase {
    private final InspectionRepository repository;

    public CreateInspectionUseCase(InspectionRepository repository) {
        this.repository = repository;
    }

    public Inspection handle(CreateInspectionCommand cmd) {
        var inspection = Inspection.requestFor(new VehicleId(cmd.vehicleId()));
        return repository.save(inspection);
    }
}