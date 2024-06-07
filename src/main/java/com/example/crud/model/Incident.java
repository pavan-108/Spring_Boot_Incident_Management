package com.example.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "\"incident\"")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String incidentId;
    private String reporterName;
    private String details;
    private LocalDateTime reportedDate;
    private String priority;
    private String status;
    private String type; // Enterprise or Government

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}