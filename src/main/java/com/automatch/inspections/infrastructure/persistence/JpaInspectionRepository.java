package com.automatch.inspections.infrastructure.persistence;

import com.automatch.inspections.infrastructure.persistence.entity.InspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface JpaInspectionRepository extends JpaRepository<InspectionEntity, java.util.UUID> {
    List<InspectionEntity> findByVehicleId(UUID vehicleId);
}