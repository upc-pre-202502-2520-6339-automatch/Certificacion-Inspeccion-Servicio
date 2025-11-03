package com.automatch.inspections.infrastructure.persistence;

import com.automatch.inspections.domain.model.Certification;
import com.automatch.inspections.domain.model.vo.VehicleId;
import com.automatch.inspections.domain.ports.CertificationRepository;
import com.automatch.inspections.infrastructure.mapper.PersistenceMapper;
import com.automatch.inspections.infrastructure.persistence.entity.CertificationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SpringDataCertificationRepository implements CertificationRepository {

    private final JpaCertificationRepository jpa;

    public SpringDataCertificationRepository(JpaCertificationRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Certification save(Certification certification) {
        CertificationEntity entity = PersistenceMapper.toEntity(certification);
        CertificationEntity saved = jpa.save(entity);
        // para simplificar retorno, no necesitamos reconstruir dominio completo
        return certification;
    }

    @Override
    public Optional<Certification> findById(UUID id) {
        return jpa.findById(id)
                .map(e -> Certification.issue(e.getInspectionId(), new VehicleId(e.getVehicleId()), e.getExpiresAt()));
    }

    @Override
    public List<Certification> findByVehicleId(UUID vehicleId) {
        return jpa.findByVehicleId(vehicleId).stream()
                .map(e -> Certification.issue(e.getInspectionId(), new VehicleId(e.getVehicleId()), e.getExpiresAt()))
                .toList();
    }
}