package com.example.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class IncidentDTO {
    private String reporterName;
    private String details;
    private String reportedDate;
    private String priority;
    private String status;
    private String type;

    // Getters and setters
}
