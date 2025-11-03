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
        var d = Inspection.requestFor(new VehicleId(e.getVehicleId()));
        // forzamos los campos generados
        // (reconstrucción simple, para un proyecto real podrías usar un constructor
        // package-private)
        try {
            var idField = Inspection.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(d, e.getId());
            var statusField = Inspection.class.getDeclaredField("status");
            statusField.setAccessible(true);
            statusField.set(d, e.getStatus());
            var sch = Inspection.class.getDeclaredField("scheduledAt");
            sch.setAccessible(true);
            sch.set(d, e.getScheduledAt());
            var insp = Inspection.class.getDeclaredField("inspectorId");
            insp.setAccessible(true);
            insp.set(d, e.getInspectorId() == null ? null : new InspectorId(e.getInspectorId()));
            var notes = Inspection.class.getDeclaredField("notes");
            notes.setAccessible(true);
            notes.set(d, e.getNotes());
        } catch (Exception ignored) {
        }
        return d;
    }

    public static CertificationEntity toEntity(Certification c) {
        var e = new CertificationEntity();
        e.setId(c.getId());
        e.setInspectionId(c.getInspectionId());
        e.setVehicleId(c.getVehicleId().value());
        e.setIssuedAt(c.getIssuedAt());
        e.setExpiresAt(c.getExpiresAt());
        e.setStatus(c.getStatus());
        e.setCreatedAt(java.time.Instant.now());
        return e;
    }
}