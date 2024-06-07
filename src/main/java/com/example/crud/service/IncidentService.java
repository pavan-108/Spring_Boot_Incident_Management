package com.example.crud.service;

import com.example.crud.dto.IncidentDTO;
import com.example.crud.model.Incident;
import com.example.crud.model.User;
import com.example.crud.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserService userService;

    public Incident createIncident(IncidentDTO incidentDTO) {
        Optional<User> user = userService.getUserByUsername(incidentDTO.getReporterName());
        if (user.isPresent()) {
            Incident incident = new Incident();
            incident.setUser(user.get());
            incident.setReporterName(incidentDTO.getReporterName());
            incident.setDetails(incidentDTO.getDetails());
            incident.setReportedDate(LocalDateTime.parse(incidentDTO.getReportedDate(), DateTimeFormatter.ISO_DATE_TIME));
            incident.setPriority(incidentDTO.getPriority());
            incident.setStatus(incidentDTO.getStatus());
            incident.setType(incidentDTO.getType());
            incident.setIncidentId(generateIncidentId());
            return incidentRepository.save(incident);
        }
        return null;
    }

    public List<Incident> getIncidentsByUserId(Long userId) {
        return incidentRepository.findByUserId(userId);
    }

    private String generateIncidentId() {
        return "RMG" + (int)(Math.random() * 90000) + 10000 + LocalDate.now().getYear();
    }
}
