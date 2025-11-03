package com.automatch.inspections.interfaces.web;

import com.automatch.inspections.application.CertificationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<?> issue(@PathVariable UUID inspectionId,
            @RequestBody Map<String, String> body) {

        UUID vehicleId = UUID.fromString(body.get("vehicleId"));
        LocalDate expiresAt = LocalDate.parse(body.get("expiresAt"));

        var c = app.issue(inspectionId, vehicleId, expiresAt);

        return ResponseEntity.ok(Map.of(
                "certificationId", c.getId(),
                "inspectionId", c.getInspectionId(),
                "vehicleId", c.getVehicleId().value(),
                "issuedAt", c.getIssuedAt(),
                "expiresAt", c.getExpiresAt()));
    }
}