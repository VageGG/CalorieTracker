package com.example.calorietracker.controller;

import com.example.calorietracker.model.Dish;
import com.example.calorietracker.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable UUID id) {
        Optional<Dish> dish = dishService.getDishById(id);
        return dish.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Dish createDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable UUID id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
