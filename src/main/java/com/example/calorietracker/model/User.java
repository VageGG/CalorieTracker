package com.example.calorietracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private int age;
    private double weight;
    private double height;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    private int dailyCalorieNorm;
}