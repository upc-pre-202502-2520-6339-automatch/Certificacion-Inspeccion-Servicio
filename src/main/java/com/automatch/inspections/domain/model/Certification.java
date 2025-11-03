package com.automatch.inspections.domain.model;

import com.automatch.inspections.domain.model.enums.CertificationStatus;
import com.automatch.inspections.domain.model.vo.VehicleId;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class Certification {
    private final UUID id; // generado en servidor
    private final UUID inspectionId;
    private final VehicleId vehicleId;
    private final LocalDate issuedAt;
    private final LocalDate expiresAt;
    private CertificationStatus status;
    private Instant createdAt;

    private Certification(UUID inspectionId, VehicleId vehicleId, LocalDate expiresAt) {
        this.id = UUID.randomUUID();
        this.inspectionId = inspectionId;
        this.vehicleId = vehicleId;
        this.issuedAt = LocalDate.now();
        this.expiresAt = expiresAt;
        this.status = CertificationStatus.ACTIVE;
        this.createdAt = Instant.now();
    }

    public static Certification issue(UUID inspectionId, VehicleId vehicleId, LocalDate expiresAt) {
        return new Certification(inspectionId, vehicleId, expiresAt);
    }

    public UUID getId() {
        return id;
    }

    public UUID getInspectionId() {
        return inspectionId;
    }

    public VehicleId getVehicleId() {
        return vehicleId;
    }

    public LocalDate getIssuedAt() {
        return issuedAt;
    }

    public LocalDate getExpiresAt() {
        return expiresAt;
    }

    public CertificationStatus getStatus() {
        return status;
    }

    public void revoke() {
        this.status = CertificationStatus.REVOKED;
    }
}