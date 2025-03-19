package com.example.calorietracker.controller;

import com.example.calorietracker.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily/{userId}")
    public int getDailyCalories(@PathVariable UUID userId, @RequestParam LocalDate date) {
        return reportService.getDailyCalories(userId, date);
    }

    @GetMapping("/check/{userId}")
    public boolean isWithinCalorieLimit(@PathVariable UUID userId, @RequestParam LocalDate date) {
        return reportService.isWithinCalorieLimit(userId, date);
    }

    @GetMapping("/history/{userId}")
    public List<?> getMealHistory(@PathVariable UUID userId) {
        return reportService.getMealHistory(userId);
    }
}