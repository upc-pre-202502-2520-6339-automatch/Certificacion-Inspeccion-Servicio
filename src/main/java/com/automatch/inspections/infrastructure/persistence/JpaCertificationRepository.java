package com.automatch.inspections.infrastructure.persistence;

import com.automatch.inspections.infrastructure.persistence.entity.CertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaCertificationRepository extends JpaRepository<CertificationEntity, UUID> {
    List<CertificationEntity> findByVehicleId(UUID vehicleId);
}
