package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.filrouge.gymcommunity.model.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_nutrition")
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNutrition extends BaseEntity<Integer> {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AppUser user;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender sex;

    @Column(nullable = false)
    private float height;

    @Column(nullable = false)
    private float weight;

    @Column(nullable = false)
    private float targetWeight;

    @Column(nullable = false)
    private LocalDate targetDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DietStyle dietStyle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkoutLevel workoutLevel;

    @Column(nullable = false)
    private float dailyCalorieGoal;

    @Column(nullable = false)
    private float carbs;

    @Column(nullable = false)
    private float proteins;

    @Column(nullable = false)
    private float fats;

    @Column(nullable = false)
    private double proteinPercentage;

    @Column(nullable = false)
    private double carbPercentage;

    @Column(nullable = false)
    private double fatPercentage;
}
