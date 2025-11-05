package com.automatch.inspections.infrastructure.mapper;

import com.automatch.inspections.domain.model.*;
import com.automatch.inspections.domain.model.vo.*;
import com.automatch.inspections.infrastructure.persistence.entity.*;

public class PersistenceMapper {

    public static InspectionEntity toEntity(Inspection domain) {
        var e = new InspectionEntity();
        e.setId(domain.getId());
        e.setVehicleId(domain.getVehicleId().value());
        e.setStatus(domain.getStatus());
        e.setScheduledAt(domain.getScheduledAt());
        e.setInspectorId(domain.getInspectorId() == null ? null : domain.getInspectorId().value());
        e.setNotes(domain.getNotes());
        e.setCreatedAt(domain.getCreatedAt());
        e.setUpdatedAt(domain.getUpdatedAt());
        return e;
    }

    public static Inspection toDomain(InspectionEntity e) {
        return new Inspection(
                e.getId(),
                new VehicleId(e.getVehicleId()),
                e.getStatus(),
                e.getScheduledAt(),
                e.getInspectorId() == null ? null : new InspectorId(e.getInspectorId()),
                e.getNotes(),
                e.getCreatedAt(),
                e.getUpdatedAt());
    }

    public static CertificationEntity toEntity(Certification c) {
        var e = new CertificationEntity();

        if (c.getId() != null) {
            e.setId(c.getId());
        }

        e.setInspectionId(c.getInspectionId());
        e.setVehicleId(c.getVehicleId().value());
        e.setIssuedAt(c.getIssuedAt());
        e.setExpiresAt(c.getExpiresAt());
        e.setStatus(c.getStatus());
        e.setCreatedAt(java.time.Instant.now());
        return e;
    }

    public static Certification toDomain(CertificationEntity e) throws Exception {
        Certification c = Certification.issue(
                e.getInspectionId(),
                new VehicleId(e.getVehicleId()),
                e.getExpiresAt());

        // set id
        var idField = Certification.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(c, e.getId());

        // set status
        var statusField = Certification.class.getDeclaredField("status");
        statusField.setAccessible(true);
        statusField.set(c, e.getStatus());

        // set createdAt
        var createdField = Certification.class.getDeclaredField("createdAt");
        createdField.setAccessible(true);
        createdField.set(c, e.getCreatedAt());

        return c;
    }
}