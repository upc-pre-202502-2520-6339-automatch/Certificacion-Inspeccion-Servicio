package com.automatch.inspections.interfaces.web;

import com.automatch.inspections.application.CertificationApplicationService;
import com.automatch.inspections.interfaces.web.dto.IssueCertificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/certifications")
public class CertificationController {

    private final CertificationApplicationService app;

    public CertificationController(CertificationApplicationService app) {
        this.app = app;
    }

    @PostMapping("/inspection/{inspectionId}")
    public ResponseEntity<?> issue(
            @PathVariable UUID inspectionId,
            @RequestBody IssueCertificationRequest request) {

        var c = app.issue(inspectionId, request.vehicleId(), request.expiresAt());

        return ResponseEntity.ok(Map.of(
                "certificationId", c.getId(),
                "inspectionId", c.getInspectionId(),
                "vehicleId", c.getVehicleId().value(),
                "issuedAt", c.getIssuedAt(),
                "expiresAt", c.getExpiresAt()));
    }

    @PatchMapping("/{certificationId}/revoke")
    public ResponseEntity<?> revoke(@PathVariable UUID certificationId) {

        var c = app.revoke(certificationId);

        return ResponseEntity.ok(Map.of(
                "certificationId", c.getId(),
                "status", c.getStatus().name()));
    }
}