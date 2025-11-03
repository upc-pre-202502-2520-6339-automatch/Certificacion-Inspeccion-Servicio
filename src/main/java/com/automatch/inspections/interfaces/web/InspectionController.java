package com.automatch.inspections.interfaces.web;

import com.automatch.inspections.application.InspectionApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inspections")
public class InspectionController {

    private final InspectionApplicationService app;

    public InspectionController(InspectionApplicationService app) {
        this.app = app;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> body) {
        UUID vehicleId = UUID.fromString(body.get("vehicleId"));
        var i = app.requestInspection(vehicleId);
        return ResponseEntity.ok(Map.of(
                "inspectionId", i.getId(),
                "status", i.getStatus().toString()));
    }

    @PostMapping("/{inspectionId}/schedule")
    public ResponseEntity<?> schedule(@PathVariable UUID inspectionId,
            @RequestBody Map<String, String> body) {

        LocalDateTime when = LocalDateTime.parse(body.get("when"));
        UUID inspectorId = UUID.fromString(body.get("inspectorId"));

        var i = app.schedule(inspectionId, when, inspectorId);

        return ResponseEntity.ok(Map.of(
                "inspectionId", i.getId(),
                "status", i.getStatus().toString(),
                "scheduledAt", i.getScheduledAt()));
    }

    @PostMapping("/{inspectionId}/result")
    public ResponseEntity<?> recordResult(@PathVariable UUID inspectionId,
            @RequestBody Map<String, Object> body) {

        boolean passed = (boolean) body.get("passed");
        String notes = (String) body.get("notes");

        var i = app.recordResult(inspectionId, passed, notes);

        return ResponseEntity.ok(Map.of(
                "inspectionId", i.getId(),
                "status", i.getStatus().toString(),
                "notes", i.getNotes()));
    }
}