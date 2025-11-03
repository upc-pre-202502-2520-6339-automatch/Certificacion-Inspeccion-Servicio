package com.automatch.inspections.infrastructure.persistence;

import com.automatch.inspections.domain.model.Inspection;
import com.automatch.inspections.domain.ports.InspectionRepository;
import com.automatch.inspections.infrastructure.mapper.PersistenceMapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import static com.automatch.inspections.infrastructure.mapper.PersistenceMapper.*;

@Repository
public class SpringDataInspectionRepository implements InspectionRepository {
    private final JpaInspectionRepository jpa;

    public SpringDataInspectionRepository(JpaInspectionRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Inspection save(Inspection inspection) {
        var saved = jpa.save(PersistenceMapper.toEntity(inspection));
        return toDomain(saved);
    }

    @Override
    public Optional<Inspection> findById(UUID id) {
        return jpa.findById(id).map(PersistenceMapper::toDomain);
    }

    @Override
    public List<Inspection> findByVehicleId(UUID vehicleId) {
        return jpa.findByVehicleId(vehicleId).stream().map(PersistenceMapper::toDomain).toList();
    }
}