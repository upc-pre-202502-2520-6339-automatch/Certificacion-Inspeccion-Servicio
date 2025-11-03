package com.automatch.inspections.application;

import com.automatch.inspections.domain.model.Certification;
import com.automatch.inspections.domain.model.vo.VehicleId;
import com.automatch.inspections.domain.ports.CertificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CertificationApplicationService {

    private final CertificationRepository repository;

    public CertificationApplicationService(CertificationRepository repository) {
        this.repository = repository;
    }

    public Certification issue(UUID inspectionId, UUID vehicleId, LocalDate expiresAt) {
        Certification c = Certification.issue(inspectionId, new VehicleId(vehicleId), expiresAt);
        return repository.save(c); // <<<< AQUI GUARDA REALMENTE
    }

    public Certification revoke(UUID certificationId) {
        var c = repository.findById(certificationId)
                .orElseThrow(() -> new RuntimeException("No existe certificado " + certificationId));

        c.revoke();
        return repository.save(c); // <<<< AQUI SE ACTUALIZA REALMENTE
    }
}