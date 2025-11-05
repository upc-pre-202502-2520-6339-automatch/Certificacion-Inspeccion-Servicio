package com.automatch.inspections.domain.model;

import com.automatch.inspections.domain.model.enums.InspectionStatus;
import com.automatch.inspections.domain.model.vo.VehicleId;
import com.automatch.inspections.domain.model.vo.InspectorId;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class Inspection {
    private final UUID id;
    private final VehicleId vehicleId;
    private InspectionStatus status;
    private LocalDateTime scheduledAt;
    private InspectorId inspectorId;
    private String notes;
    private Instant createdAt;
    private Instant updatedAt;

    private Inspection(UUID id, VehicleId vehicleId) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.status = InspectionStatus.REQUESTED;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    public Inspection(UUID id,
            VehicleId vehicleId,
            InspectionStatus status,
            LocalDateTime scheduledAt,
            InspectorId inspectorId,
            String notes,
            Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.status = status;
        this.scheduledAt = scheduledAt;
        this.inspectorId = inspectorId;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Inspection requestFor(VehicleId vehicleId) {
        return new Inspection(UUID.randomUUID(), vehicleId);
    }

    public void schedule(LocalDateTime when, InspectorId inspector) {
        if (status != InspectionStatus.REQUESTED && status != InspectionStatus.CANCELLED)
            throw new IllegalStateException("Solo se agenda desde REQUESTED o CANCELLED");
        this.scheduledAt = when;
        this.inspectorId = inspector;
        this.status = InspectionStatus.SCHEDULED;
        this.updatedAt = Instant.now();
    }

    public void recordResult(boolean passed, String notes) {
        if (status != InspectionStatus.SCHEDULED && status != InspectionStatus.IN_PROGRESS)
            throw new IllegalStateException("Debe estar SCHEDULED o IN_PROGRESS");
        this.status = passed ? InspectionStatus.PASSED : InspectionStatus.FAILED;
        this.notes = notes;
        this.updatedAt = Instant.now();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public VehicleId getVehicleId() {
        return vehicleId;
    }

    public InspectionStatus getStatus() {
        return status;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public InspectorId getInspectorId() {
        return inspectorId;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}