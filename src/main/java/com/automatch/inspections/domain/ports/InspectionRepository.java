package com.automatch.inspections.domain.ports;

import com.automatch.inspections.domain.model.Inspection;
import java.util.*;

public interface InspectionRepository {
    Inspection save(Inspection inspection);

    Optional<Inspection> findById(UUID id);

    List<Inspection> findByVehicleId(UUID vehicleId);
}