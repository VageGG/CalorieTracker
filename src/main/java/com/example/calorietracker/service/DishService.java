package com.example.calorietracker.service;

import com.example.calorietracker.model.Dish;
import com.example.calorietracker.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(UUID id) {
        return dishRepository.findById(id);
    }

    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public void deleteDish(UUID id) {
        dishRepository.deleteById(id);
    }
}
