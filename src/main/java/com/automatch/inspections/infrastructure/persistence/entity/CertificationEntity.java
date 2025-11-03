package com.automatch.inspections.infrastructure.persistence.entity;

import com.automatch.inspections.domain.model.enums.CertificationStatus;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "certifications")
public class CertificationEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID inspectionId;
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID vehicleId;
    private LocalDate issuedAt;
    private LocalDate expiresAt;
    @Enumerated(EnumType.STRING)
    private CertificationStatus status;
    private Instant createdAt;
}