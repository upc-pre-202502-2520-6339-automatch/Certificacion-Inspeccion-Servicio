package com.automatch.inspections.application.usecase;

import com.automatch.inspections.application.dto.IssueCertificationCommand;
import com.automatch.inspections.domain.model.Certification;
import com.automatch.inspections.domain.model.enums.InspectionStatus;
import com.automatch.inspections.domain.ports.CertificationRepository;
import com.automatch.inspections.domain.ports.InspectionRepository;
import com.automatch.inspections.domain.model.vo.VehicleId;
import java.util.UUID;

public class IssueCertificationUseCase {
    private final CertificationRepository certRepo;
    private final InspectionRepository inspRepo;

    public IssueCertificationUseCase(CertificationRepository c, InspectionRepository i) {
        this.certRepo = c;
        this.inspRepo = i;
    }

    public Certification handle(UUID inspectionId, IssueCertificationCommand cmd) {
        var inspection = inspRepo.findById(inspectionId)
                .orElseThrow(() -> new IllegalArgumentException("Inspección no encontrada"));
        if (inspection.getStatus() != InspectionStatus.PASSED)
            throw new IllegalStateException("Solo se certifica una inspección aprobada (PASSED)");
        var cert = Certification.issue(inspection.getId(),
                new VehicleId(inspection.getVehicleId().value()),
                cmd.expiresAt());
        return certRepo.save(cert);
    }
}