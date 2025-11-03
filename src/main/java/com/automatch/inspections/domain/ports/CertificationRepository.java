package com.automatch.inspections.domain.ports;

import com.automatch.inspections.domain.model.Certification;
import java.util.*;

public interface CertificationRepository {
    Certification save(Certification certification);

    Optional<Certification> findById(UUID id);

    List<Certification> findByVehicleId(UUID vehicleId);
}