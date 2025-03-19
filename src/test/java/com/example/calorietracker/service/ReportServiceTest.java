package com.example.calorietracker.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.*;
import com.example.calorietracker.model.*;
import com.example.calorietracker.repository.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    @DisplayName("Проверка расчета калорий за день без приемов пищи")
    void testGetDailyCalories() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        when(mealRepository.findByUserIdAndDate(userId, date)).thenReturn(Collections.emptyList());
        assertEquals(0, reportService.getDailyCalories(userId, date));
    }

    @Test
    @DisplayName("Проверка расчета калорий за день с приемами пищи")
    void testGetDailyCaloriesWithMeals() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        Dish dish1 = new Dish(UUID.randomUUID(), "Apple", 100, 0.5, 0.2, 25);
        Dish dish2 = new Dish(UUID.randomUUID(), "Bread", 200, 5, 2, 40);
        Meal meal = new Meal(UUID.randomUUID(), null, Arrays.asList(dish1, dish2), date);
        when(mealRepository.findByUserIdAndDate(userId, date)).thenReturn(Arrays.asList(meal));
        assertEquals(300, reportService.getDailyCalories(userId, date));
    }

    @Test
    @DisplayName("Проверка, укладывается ли пользователь в лимит калорий")
    void testIsWithinCalorieLimit() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        User user = new User(userId, "John Doe", "john@example.com", 30, 75, 175, Goal.MAINTENANCE, 2000);
        Dish dish = new Dish(UUID.randomUUID(), "Rice", 500, 4, 1, 100);
        Meal meal = new Meal(UUID.randomUUID(), user, Arrays.asList(dish), date);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mealRepository.findByUserIdAndDate(userId, date)).thenReturn(Arrays.asList(meal, meal, meal));
        assertTrue(reportService.isWithinCalorieLimit(userId, date));
    }

    @Test
    @DisplayName("Проверка превышения лимита калорий")
    void testIsOverCalorieLimit() {
        UUID userId = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        User user = new User(userId, "John Doe", "john@example.com", 30, 75, 175, Goal.MAINTENANCE, 1000);
        Dish dish = new Dish(UUID.randomUUID(), "Burger", 600, 25, 30, 50);
        Meal meal = new Meal(UUID.randomUUID(), user, Arrays.asList(dish), date);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mealRepository.findByUserIdAndDate(userId, date)).thenReturn(Arrays.asList(meal, meal));
        assertFalse(reportService.isWithinCalorieLimit(userId, date));
    }

    @Test
    @DisplayName("Проверка получения истории приемов пищи")
    void testGetMealHistory() {
        UUID userId = UUID.randomUUID();
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.minusDays(1);
        Meal meal1 = new Meal(UUID.randomUUID(), null, Collections.emptyList(), date1);
        Meal meal2 = new Meal(UUID.randomUUID(), null, Collections.emptyList(), date2);
        when(mealRepository.findByUserIdOrderByDateDesc(userId)).thenReturn(Arrays.asList(meal1, meal2));
        List<Meal> history = reportService.getMealHistory(userId);
        assertEquals(2, history.size());
        assertEquals(date1, history.get(0).getDate());
        assertEquals(date2, history.get(1).getDate());
    }
}
