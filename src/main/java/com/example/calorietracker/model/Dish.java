package com.example.calorietracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
}

