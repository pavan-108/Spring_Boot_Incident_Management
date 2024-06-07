package com.example.crud.controller;

import com.example.crud.dto.IncidentDTO;
import com.example.crud.model.Incident;
import com.example.crud.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    private IncidentService incidentService;

    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody IncidentDTO incidentDTO) {
        Incident incident = incidentService.createIncident(incidentDTO);
        if (incident != null) {
            return ResponseEntity.ok(incident);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Incident>> getIncidentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(incidentService.getIncidentsByUserId(userId));
    }
}
