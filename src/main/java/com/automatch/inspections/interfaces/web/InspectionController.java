package com.automatch.inspections.interfaces.web;

import com.automatch.inspections.application.InspectionApplicationService;
import com.automatch.inspections.interfaces.web.dto.CreateInspectionRequest;
import com.automatch.inspections.interfaces.web.dto.RecordInspectionResultRequest;
import com.automatch.inspections.interfaces.web.dto.ScheduleInspectionRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody CreateInspectionRequest request) {
        var i = app.requestInspection(request.vehicleId());
        return ResponseEntity.ok(Map.of(
                "inspectionId", i.getId(),
                "status", i.getStatus().toString()));
    }

    @PostMapping("/{inspectionId}/schedule")
    public ResponseEntity<?> schedule(
            @PathVariable UUID inspectionId,
            @RequestBody ScheduleInspectionRequest request) {

        var i = app.schedule(inspectionId, request.when(), request.inspectorId());

        return ResponseEntity.ok(Map.of(
                "inspectionId", i.getId(),
                "status", i.getStatus().toString(),
                "scheduledAt", i.getScheduledAt()));
    }

    @PostMapping("/{inspectionId}/result")
    public ResponseEntity<?> recordResult(
            @PathVariable UUID inspectionId,
            @RequestBody RecordInspectionResultRequest request) {

        var i = app.recordResult(inspectionId, request.passed(), request.notes());

        return ResponseEntity.ok(Map.of(
                "inspectionId", i.getId(),
                "status", i.getStatus().toString(),
                "notes", i.getNotes()));
    }
}