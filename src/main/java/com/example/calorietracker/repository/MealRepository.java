package com.example.calorietracker.repository;

import com.example.calorietracker.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {
    List<Meal> findByUserIdAndDate(UUID userId, LocalDate date);
    List<Meal> findByUserIdOrderByDateDesc(UUID userId);
}