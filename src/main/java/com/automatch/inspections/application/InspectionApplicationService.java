package com.automatch.inspections.application;

import com.automatch.inspections.domain.model.Inspection;
import com.automatch.inspections.domain.model.vo.InspectorId;
import com.automatch.inspections.domain.model.vo.VehicleId;
import com.automatch.inspections.domain.ports.InspectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InspectionApplicationService {

    private final InspectionRepository repository;

    public InspectionApplicationService(InspectionRepository repository) {
        this.repository = repository;
    }

    public Inspection requestInspection(UUID vehicleId) {
        Inspection inspection = Inspection.requestFor(new VehicleId(vehicleId));
        return repository.save(inspection); // AQUI GUARDA REALMENTE
    }

    public Inspection recordResult(UUID inspectionId, boolean passed, String notes) {
        var inspection = repository.findById(inspectionId)
                .orElseThrow(() -> new RuntimeException("No existe inspección " + inspectionId));
        inspection.recordResult(passed, notes);
        return repository.save(inspection); // AQUI GUARDA EL RESULTADO REALMENTE
    }

    public Inspection schedule(UUID inspectionId, LocalDateTime when, UUID inspectorId) {
        var ins = repository.findById(inspectionId).orElseThrow();
        ins.schedule(when, inspectorId == null ? null : new InspectorId(inspectorId));
        return repository.save(ins);
    }
}