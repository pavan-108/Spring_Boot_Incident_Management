package com.example.crud.repository;

import com.example.crud.model.Incident;
import com.example.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByUserId(Long userId);
}