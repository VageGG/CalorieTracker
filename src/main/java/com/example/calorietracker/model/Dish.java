package com.example.calorietracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String name;

    @Min(0)
    private int calories;

    @Min(0)
    private double proteins;

    @Min(0)
    private double fats;

    @Min(0)
    private double carbohydrates;
}

