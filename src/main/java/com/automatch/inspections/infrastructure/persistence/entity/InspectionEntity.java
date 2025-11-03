package com.automatch.inspections.infrastructure.persistence.entity;

import com.automatch.inspections.domain.model.enums.InspectionStatus;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "inspections")
public class InspectionEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID vehicleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InspectionStatus status;
    private LocalDateTime scheduledAt;
    @Column(columnDefinition = "uuid")
    private UUID inspectorId;
    private String notes;
    private Instant createdAt;
    private Instant updatedAt;
}