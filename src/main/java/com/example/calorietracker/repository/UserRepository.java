package com.example.calorietracker.repository;

import com.example.calorietracker.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}