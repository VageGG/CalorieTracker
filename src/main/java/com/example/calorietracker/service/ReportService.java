package com.example.calorietracker.service;

import com.example.calorietracker.model.Meal;
import com.example.calorietracker.model.User;
import com.example.calorietracker.repository.MealRepository;
import com.example.calorietracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public int getDailyCalories(UUID userId, LocalDate date) {
        List<Meal> meals = mealRepository.findByUserIdAndDate(userId, date);
        return meals.stream().flatMap(meal -> meal.getDishes().stream()).mapToInt(dish -> dish.getCalories()).sum();
    }

    public boolean isWithinCalorieLimit(UUID userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return getDailyCalories(userId, date) <= user.getDailyCalorieNorm();
    }

    public List<Meal> getMealHistory(UUID userId) {
        return mealRepository.findByUserIdOrderByDateDesc(userId);
    }
}