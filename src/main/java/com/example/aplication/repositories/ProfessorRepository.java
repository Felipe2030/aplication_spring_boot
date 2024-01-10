package com.example.aplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aplication.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}
