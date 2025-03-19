package com.example.calorietracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Min(10)
    @Max(115)
    private int age;

    @Min(30)
    @Max(250)
    private double weight;

    @Min(80)
    @Max(250)
    private double height;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    private int dailyCalorieNorm;
}